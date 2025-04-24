#!/usr/bin/env python3

import redis
import random
import string
from collections import defaultdict

class ShortURLService:
    def __init__(self):
        self.r = redis.Redis(
            host='localhost',
            port=6379,
            db=0,
            decode_responses=True
        )
        self.init_counters()  # Αρχικοποίηση μετρητών

    def init_counters(self):
        """Αρχικοποίηση μετρητών αν δεν υπάρχουν."""
        if not self.r.exists("total_users"):
            self.r.set("total_users", 0)
        if not self.r.exists("total_urls"):
            self.r.set("total_urls", 0)

    def generate_short_code(self):
        """Δημιουργία τυχαίου short code 6 χαρακτήρων."""
        return ''.join(random.choices(string.ascii_lowercase + string.digits, k=6))

    def insert_url(self, username, long_url):
        """Εισαγωγή νέου URL με αποθήκευση μετρητών."""
        # Έλεγχος ύπαρξης URL
        existing_short = self.r.get(f"long_url:{long_url}")
        if existing_short:
            print("This long URL already exists with short code:", existing_short)
            return existing_short

        # Δημιουργία μοναδικού short code
        while True:
            short_code = self.generate_short_code()
            if not self.r.exists(f"url:{short_code}"):
                break

        # Αποθήκευση δεδομένων
        self.r.hset(f"url:{short_code}", mapping={
            'long_url': long_url,
            'creator': username,
            'clicks': 0
        })
        self.r.set(f"long_url:{long_url}", short_code)

        # Ενημέρωση μετρητών και συνόλων
        if not self.r.exists(f"user:{username}:count"):
            self.r.incr("total_users")
        self.r.sadd(f"user:{username}:urls", short_code)
        self.r.incr(f"user:{username}:count")
        self.r.incr("total_urls")

        print(f"\nCreated new short URL: http://my.com/{short_code}")
        return short_code

    def query_url(self, short_code):
        """Ανάκτηση πρωτότυπου URL και αύξηση μετρητή clicks."""
        url_data = self.r.hgetall(f"url:{short_code}")
        if not url_data:
            print("Short URL not found!")
            return None

        self.r.hincrby(f"url:{short_code}", 'clicks', 1)
        return url_data['long_url']

    def get_stats(self):
        """Στατιστικά με βελτιστοποιημένη ανάκτηση δεδομένων."""
        stats = {
            'total_users': int(self.r.get("total_users")),
            'total_urls': int(self.r.get("total_urls")),
            'user_stats': defaultdict(dict)
        }

        total_clicks = 0
        # Χρήση scan_iter για ασφαλή αναζήτηση χρηστών
        for user_key in self.r.scan_iter("user:*:count"):
            username = user_key.split(':')[1]
            url_count = int(self.r.get(user_key))
            user_urls = self.r.smembers(f"user:{username}:urls")

            user_clicks = 0
            for short_code in user_urls:
                clicks = int(self.r.hget(f"url:{short_code}", 'clicks'))
                user_clicks += clicks
                total_clicks += clicks

            stats['user_stats'][username] = {
                'url_count': url_count,
                'total_clicks': user_clicks,
                'average_clicks': user_clicks / url_count if url_count > 0 else 0
            }

        stats['average_clicks'] = total_clicks / stats['total_urls'] if stats['total_urls'] > 0 else 0
        return stats

def display_stats(stats):
    """Οπτικοποίηση στατιστικών."""
    print("\n=== General Statistics ===")
    print(f"Total users: {stats['total_users']}")
    print(f"Total URLs: {stats['total_urls']}")
    print(f"Average clicks per URL: {stats['average_clicks']:.2f}")

    print("\n=== Στατιστικά Χρηστών ===")
    for username, user_data in stats['user_stats'].items():
        print(f"\nUser: {username}")
        print(f"  URLs created: {user_data['url_count']}")
        print(f"  Total clicks: {user_data['total_clicks']}")
        print(f"  Average clicks: {user_data['average_clicks']:.2f}")

def main():
    service = ShortURLService()
    print("=== Short URL Service ===")
    
    while True:
        print("\n1. Create short URL")
        print("2. Get original URL")
        print("3. Show statistics")
        print("4. Exit")
        
        choice = input("Choose an option (1-4): ").strip()
        
        if choice == "1":
            username = input("Enter username: ").strip()
            long_url = input("Enter long URL: ").strip()
            if not username or not long_url:
                print("Error: Username and URL cannot be empty!")
                continue
            service.insert_url(username, long_url)
        
        elif choice == "2":
            short = input("Enter short code: http://my.com/").strip()
            if not short:
                print("Error: Short code cannot be empty!")
                continue
                
            long_url = service.query_url(short)
            if long_url:
                print(f"\nOriginal URL: {long_url}")
        
        elif choice == "3":
            stats = service.get_stats()
            display_stats(stats)
        
        elif choice == "4":
            print("Exiting...")
            break
        
        else:
            print("Invalid choice. Please enter a number between 1 and 4.")


if __name__ == "__main__":
    main()
