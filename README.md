# Programming Project 3: The Knapsack Problem (Space Shuttle Payload)

## 1. Project Overview
This project simulates a space shuttle mission with a strict **700 kg** payload limit. Using a list of 12 experiments, the program evaluates different algorithmic strategies to find the combination that yields the highest scientific rating.

## 2. Team Members & Roles
* **Bryant Gonzalez** - Communications Lead 
* **Alex Gonzalez** - Verification Lead
* **Logan Chess** - Implementation Lead 

## 3. Implemented Strategies
The program compares four distinct approaches:
1.  **Highest Rating First (Greedy):** Picks experiments with the highest individual rating.
2.  **Lightest First (Greedy):** Prioritizes the lowest weight to fit more items.
3.  **Best Rating-to-Weight Ratio First (Greedy):** Picks items based on "efficiency" (Rating / Weight).
4.  **Brute Force / Exhaustive Search:** Checks all 4,096 possible subsets to find the absolute optimal solution.

## 4. How to Run the Program (Java)
To compile and simulate your terminal:

1.  Navigate to the `/src` directory:
    ```bash
    cd src
    ```
2.  Compile the Java files:
    ```bash
    javac *.java
    ```
3.  Run the applications:
    ```bash
    PermutationGenerator and Knapsack
    ```

## 5. Documentation
Additional artifacts are located in the `/documents` folder:
* **Project Plan:** Timeline and task breakdown.
* **Design Artifacts:** Pseudocode and architectural notes.
* **CONTRIBUTIONS.md:** Mapping of team responsibilities and evidence pointers.
* **Project3_Report:** The formal written analysis of our findings.
