include "globals.mzn";

int: no_firetrucks;
set of int: FIRETRUCKS = 1..no_firetrucks;
array[FIRETRUCKS] of int: water_cap;
array[FIRETRUCKS] of int: service_time;

%% Decision Variables - You can change anything, BUT the names of the variables.
array[FIRETRUCKS] of var int: start_fill;
array[FIRETRUCKS] of var int: start_service;
var int: makespan; 
var int: total_service;
%%% Add your code here

% Calculate filling time for each truck
array[FIRETRUCKS] of int: fill_time = [ceil(water_cap[i] / 50.0) | i in FIRETRUCKS];

% We need to narrow down the range of variables
constraint forall(i in FIRETRUCKS) (
    start_fill[i] >= 0 /\
    start_fill[i] + fill_time[i] <= 1000 /\
    start_service[i] >= start_fill[i] + fill_time[i] /\
    start_service[i] + service_time[i] <= 1000 );

% Filling constraints - using cumulative (resource limitation)
constraint cumulative(start_fill, fill_time, [1 | i in FIRETRUCKS], 3);

% Service constraints - we use disjunctive (mutual exclusion)
constraint disjunctive(start_service, service_time);

% Total service time calculation
constraint total_service = max([start_service[i] + service_time[i] | i in FIRETRUCKS]) - min([start_service[i] | i in FIRETRUCKS]);

% Makespan calculation
constraint makespan = max([start_service[i] + service_time[i] | i in FIRETRUCKS]);

% Objective: Minimize makespan
solve :: seq_search([
    int_search(start_fill, smallest, indomain_min),
    int_search(start_service, smallest, indomain_min)
]) minimize makespan;


output [" start_fill=\(start_fill);\n start_service=\(start_service);\n"]
       ++ [" total_service = \(total_service);\n makespan=\(makespan);\n"]

