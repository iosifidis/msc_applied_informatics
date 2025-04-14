%% Number of Servers and Clients and related Sets
par int: Servers;
par int: Clients;

set of int:SERVERS = 1..Servers;
set of int:CLIENTS = 1..Clients;

%% Arrays stating Problem Data
array[SERVERS] of int: Capacity;
array[SERVERS] of int: DeploymentCost;
array[CLIENTS] of int: ClientDemands ;
array[CLIENTS,SERVERS] of int: NetworkingCost;

%% Add your code here

%% Decision Variables
array[CLIENTS] of var SERVERS: client_at;
var int: total_cost;

%% Helper Variables
array[SERVERS] of var int: server_load = 
    [sum(c in CLIENTS where client_at[c] == s)(ClientDemands[c]) | s in SERVERS];

array[SERVERS] of var 0..1: server_active;

%% Constraints
constraint forall(s in SERVERS) (
    server_load[s] <= Capacity[s]);

constraint forall(s in SERVERS) (
    server_active[s] = (server_load[s] > 0));

%% Calculate total cost
var int: total_deployment_cost = sum(s in SERVERS)(server_active[s] * DeploymentCost[s]);
var int: total_networking_cost = sum(c in CLIENTS)(ClientDemands[c] * NetworkingCost[c, client_at[c]]);

constraint total_cost = total_deployment_cost + total_networking_cost;


%% Solve to minimize total cost
solve minimize total_cost;

%%% DO NOT DELETE THIS LINES
output [" client_at = \(client_at); \n total_cost=\(total_cost);\n"];