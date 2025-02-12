// Πίνακας με τις συναλλαγές
const transactions = [];

// Αναφορά στο κουμπί "Προσθήκη Συναλλαγής"
const addTransactionBtn = document.getElementById('addTransactionBtn'); // Εύρεση του κουμπιού προσθήκης
const tableBody = document.querySelector('#transactionsTable tbody'); // Εύρεση του σώματος του πίνακα για εμφάνιση δεδομένων

// Χειρισμός επιλογής αρχείου CSV
document.getElementById('fileInputBtn').addEventListener('click', () => {
    document.getElementById('fileInput').click(); // Κλικ στο κουμπί για να ανοίξει το παράθυρο επιλογής αρχείου
});

document.getElementById('fileInput').addEventListener('change', (event) => {
    const file = event.target.files[0]; // Λήψη του επιλεγμένου αρχείου 
    if (file) {
        const reader = new FileReader(); // Δημιουργία αντικειμένου FileReader
        reader.onload = function (e) { // Ορισμός συνάρτησης που θα εκτελεστεί όταν ολοκληρωθεί η ανάγνωση του αρχείου
            const csvData = e.target.result; // Λήψη των δεδομένων από το αρχείο
            processCSV(csvData); // Επεξεργασία των δεδομένων CSV
        };
        reader.readAsText(file); // Διαβάζουμε το αρχείο ως κείμενο
    }
});

// Λειτουργία εμφάνισης πίνακα
function renderTable() {
    tableBody.innerHTML = '';

    transactions.forEach((transaction, index) => {
        const row = document.createElement('tr'); // Δημιουργία νέας σειράς
        Object.keys(transaction).forEach(key => { // Προσθήκη των τιμών της συναλλαγής στα κελιά της σειρ
            const cell = document.createElement('td'); // Δημιουργία νέου κελιοϋ
            let value = transaction[key]; // Λήψη της τιμής της συναλλαγής
            if (key === 'invoice_date') { // Έλεγχος αν το κλειδί είναι ημερομηνία
                const date = new Date(value); // Δημιουργία νέας ημερομηνίας
                if (!isNaN(date)) { // Έλεγχος αν είναι έγκυρη ημερομηνία
                     value = formatDate(value); // Αν είναι έγκυρη ημερομηνία μετατροπή.
                }
             }
             cell.textContent = value; // Εμφάνιση της τιμής στο κελί
            row.appendChild(cell); // Προσθήκη του κελιού στη σειρά
        });

        const actionsCell = document.createElement('td'); // Δημιουργία κελιού για τα κουμπιά ενεργειών
        const editButton = document.createElement('button'); // Δημιουργία κουμπιού επεξεργασίας
        editButton.textContent = 'Επεξεργασία'; // Κείμενο κουμπιού
        editButton.addEventListener('click', () => editTransaction(row, index)); // Λειτουργία επεξεργασίας
        actionsCell.appendChild(editButton); // Προσθήκη του κουμπιού στο κελί

        row.appendChild(actionsCell); // Προσθήκη του κελιού με τα κουμπιά ενεργειών στη σειρά
        tableBody.appendChild(row); // Προσθήκη της σειράς στο σώμα του πίνακα
    });
}

// Αποθήκευση της συναλλαγής και προσθήκη στον πίνακα
function saveTransaction(row) {
    // Συγκέντρωση των τιμών από τα πεδία
    const newTransaction = {
        invoice_no: row.querySelector('#invoice_no').value,
        customer_id: row.querySelector('#customer_id').value,
        gender: row.querySelector('#gender').value,
        age: row.querySelector('#age').value,
        category: row.querySelector('#category').value,
        quantity: row.querySelector('#quantity').value,
        price: row.querySelector('#price').value,
        payment_method: row.querySelector('#payment_method').value,
        invoice_date: row.querySelector('#invoice_date').value,
        shopping_mall: row.querySelector('#shopping_mall').value
    };

    // Έλεγχος αν όλα τα πεδία έχουν συμπληρωθεί
    for (let key in newTransaction) {
        if (newTransaction[key] === '') {
            alert('Παρακαλώ συμπληρώστε όλα τα πεδία πριν αποθηκεύσετε!');
            return; // Τερματισμός της διαδικασίας αν λείπει κάποιο πεδίο
        }
    }

    // Προσθήκη της συναλλαγής στον πίνακα αν όλα τα πεδία είναι πλήρη
    transactions.push(newTransaction);
    renderTable(); // Ενημέρωση του πίνακα

}

// Επεξεργασία δεδομένων CSV
function processCSV(csvData) {
    const rows = csvData.split('\n'); // Διαχωρισμός των γραμμών
    transactions.length = 0; // Καθαρισμός των υπαρχόντων δεδομένων
    rows.slice(1).forEach(row => { // Παράκαμψη της πρώτης γραμμής (κεφαλίδα)
        const columns = row.split(','); // Διαχωρισμός των στηλών
        if (columns.length === 10) { // Έλεγχος αν υπάρχουν όλα τα πεδία
            transactions.push({ // Προσθήκη της συναλλαγής στον πίνακα
                invoice_no: columns[0],
                customer_id: columns[1],
                gender: columns[2],
                age: columns[3],
                category: columns[4],
                quantity: columns[5],
                price: columns[6],
                payment_method: columns[7],
                invoice_date: columns[8], //Αποθήκευση ημερομηνίας όπως είναι
                shopping_mall: columns[9]
            });
        }
    });
    renderTable();
}

// Λειτουργία εξαγωγής σε CSV
document.getElementById('exportCsvBtn').addEventListener('click', () => {
    if (transactions.length === 0) {
        alert('Δεν υπάρχουν δεδομένα προς εξαγωγή!');
        return;
    }
 
    const headers = ["Αριθμός Τιμολογίου", "ID Πελάτη", "Φύλο", "Ηλικία", "Κατηγορία", "Ποσότητα", "Τιμή", "Μέθοδος Πληρωμής", "Ημερομηνία Τιμολογίου", "Εμπορικό Κέντρο"];
    const csvRows = [headers.join(',')];
 
    transactions.forEach(transaction => {
        const row = [
            transaction.invoice_no,
            transaction.customer_id,
            transaction.gender,
            transaction.age,
            transaction.category,
            transaction.quantity,
            transaction.price,
            transaction.payment_method,
            formatDate(transaction.invoice_date),
            transaction.shopping_mall
        ];
        csvRows.push(row.join(','));
    });
 
    const csvContent = csvRows.join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
 
    const link = document.createElement('a');
    link.setAttribute('href', url);
    link.setAttribute('download', 'output_from_frontend.csv');
    link.style.display = 'none';
    document.body.appendChild(link);
 
    link.click();
    document.body.removeChild(link);
    });


// Αναφορά στο modal window και τα κουμπιά του
const modal = document.getElementById('transactionModal'); // Εύρεση του modal
const modalContent = modal.querySelector(".modal-content"); // Εύρεση του περιεχομένου του modal
const modalForm = document.getElementById('transactionForm'); // Εύρεση της φόρμας του modal
const modalSaveBtn = document.getElementById('modalSaveBtn'); // Εύρεση του κουμπιού αποθήκευσης
const modalCancelBtn = document.getElementById('modalCancelBtn'); // Εύρεση του κουμπιού ακύρωσης
const closeBtn = modal.querySelector('.close');

let currentTransactionIndex = null; // Μεταβλητή για την αποθήκευση του index της επεξεργαζόμενης εγγραφής

// Λειτουργία ανοίγματος modal
function openModal(transaction = null, index = null) {
    modal.style.display = 'block';
    modalForm.innerHTML = ''; // Καθαρισμός της φόρμας
    currentTransactionIndex = index; // Αποθήκευση του index της επεξεργαζόμενης εγγραφής
        const fields = [
            { id: 'invoice_no', label: 'Αριθμός Τιμολογίου', type: 'text' },
            { id: 'customer_id', label: 'ID Πελάτη', type: 'text' },
            { id: 'gender', label: 'Φύλο', type: 'text' },
            { id: 'age', label: 'Ηλικία', type: 'number' },
            { id: 'category', label: 'Κατηγορία', type: 'text' },
            { id: 'quantity', label: 'Ποσότητα', type: 'number' },
            { id: 'price', label: 'Τιμή', type: 'number' },
            { id: 'payment_method', label: 'Μέθοδος Πληρωμής', type: 'text' },
           { id: 'invoice_date', label: 'Ημερομηνία Τιμολογίου', type: 'date' },
            { id: 'shopping_mall', label: 'Εμπορικό Κέντρο', type: 'text' }
        ];


        fields.forEach(field => {
            const label = document.createElement('label'); // Δημιουργία label
            label.textContent = field.label; // Εισαγωγή κειμένου στο label
            label.setAttribute('for', field.id); //Σύνδεση label με input
             modalForm.appendChild(label); // Προσθήκη label στη φόρμα


             let input;
               if(field.id==='gender'){ 
                   input = document.createElement('select'); // Δημιουργία select
                     const options = ['Male', 'Female'];  // Επιλογές gender
                     options.forEach(optionValue => { // Προσθήκη των επιλογών
                         const option = document.createElement('option'); // Δημιουργία option
                         option.value = optionValue; // Ορισμός της τιμής της επιλογής
                         option.textContent=optionValue; // Εισαγωγή των επιλογών
                         input.appendChild(option); // Προσθήκη των επιλογών στο select
                     });

               }
               else{
                   input = document.createElement('input'); // Δημιουργία input
                    input.type = field.type; // Ορισμός του τύπου του input
               }

               input.id = field.id; // Ορισμός του id του input
                if (transaction) { // Έλεγχος αν υπάρχει συναλλαγή
                 input.value = transaction[field.id] || ''; // Εισαγωγή τιμής αν υπάρχει
                }
                modalForm.appendChild(input); // Προσθήκη του input στη φόρμα


         });

    }

// Λειτουργία κλεισίματος modal
function closeModal() {
    modal.style.display = 'none'; // Κλείσιμο modal
}

closeBtn.onclick = closeModal; // Κλείσιμο modal όταν πατάμε x
window.onclick = function(event) { // Κλείσιμο modal όταν πατάμε έξω από το modal
    if (event.target === modal) { // Έλεγχος αν το event target είναι το modal
        closeModal(); // Κλείσιμο του modal
    }
}


// Αλλαγή στην λειτουργία addTransactionBtn
addTransactionBtn.addEventListener('click', () => { // Προσθήκη event listener στο κουμπί
    openModal(); // Άνοιγμα του modal
});

// Λειτουργία επεξεργασίας συναλλαγής modal
function editTransaction(row, index) { // Συνάρτηση επεξεργασίας
    openModal(transactions[index], index); // Άνοιγμα του modal με τα δεδομένα της συναλλαγής
}
     modalCancelBtn.addEventListener('click', () => { // Προσθήκη event listener στο κουμπί ακύρωσης
         closeModal(); // Κλείσιμο του modal
    });
    modalSaveBtn.addEventListener('click', () => { // Προσθήκη event listener στο κουμπί αποθήκευσης
        saveModalTransaction(); // Αποθήκευση των δεδομένων του modal
    });

// Λειτουργία αποθήκευσης των δεδομένων του modal
function saveModalTransaction() {
    const newTransaction = {
        invoice_no: modalForm.querySelector('#invoice_no').value,
        customer_id: modalForm.querySelector('#customer_id').value,
        gender: modalForm.querySelector('#gender').value,
        age: modalForm.querySelector('#age').value,
        category: modalForm.querySelector('#category').value,
        quantity: modalForm.querySelector('#quantity').value,
        price: modalForm.querySelector('#price').value,
        payment_method: modalForm.querySelector('#payment_method').value,
        invoice_date:  formatDate(modalForm.querySelector('#invoice_date').value), // Μετατροπή μόνο εδώ
        shopping_mall: modalForm.querySelector('#shopping_mall').value
    };

        // Έλεγχος αν όλα τα πεδία έχουν συμπληρωθεί
        for (let key in newTransaction) { 
        if (newTransaction[key] === '') {
            alert('Παρακαλώ συμπληρώστε όλα τα πεδία πριν αποθηκεύσετε!');
            return;
        }
    }
    if (currentTransactionIndex !== null) { // Έλεγχος αν υπάρχει επεξεργασία
            transactions[currentTransactionIndex] = newTransaction; // Αντικατάσταση της συναλλαγής
    } else {
        transactions.push(newTransaction); // Προσθήκη της συναλλαγής
    }
    renderTable(); // Ενημέρωση του πίνακα
    closeModal(); // Κλείσιμο του modal
}

// Συνάρτηση για τη μετατροπή της ημερομηνίας σε σωστή μορφή
function formatDate(dateString) {
    const date = new Date(dateString); // Δημιουργία νέας ημερομηνίας
    if (isNaN(date)) { // Έλεγχος αν είναι έγκυρη ημερομηνία
        return dateString; // Επιστρέφει το αρχικό string αν δεν είναι έγκυρη ημερομηνία
    }
    const day = String(date.getDate()).padStart(2, '0'); // Προσθήκη μηδενικού αν είναι μονοψήφια ημέρα
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Προσθήκη μηδενικού αν είναι μονοψήφιος μήνας
    const year = date.getFullYear(); // Λήψη του έτους
    return `${day}/${month}/${year}`; // Επιστροφή της ημερομηνίας σε μορφή ημέρα/μήνας/έτος
}