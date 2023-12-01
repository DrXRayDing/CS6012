import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# File path
file_path = 'SelfBalancingExperiment.tsv'

# Load the data from the TSV file
# Assuming the TSV file does not have headers, you need to manually assign column names
data = pd.read_csv(file_path, header=None, sep='\t')
data.columns = ['Size', 'Tree Average Time', 'Random Average Time']  # Replace with actual column names

# Create a figure
plt.figure(figsize=(10, 6))

# Plot the data
plt.plot(data['Size'], data['Tree Average Time'], marker='o', linestyle='-', label='TreeSet')
plt.plot(data['Size'], data['Random Average Time'], marker='o', linestyle='-', label='Random BST')

# Set the title, labels, and legend of the plot
plt.title('Self-balancing BST vs regular BST Experiment')
plt.xlabel('Size')
plt.ylabel('Average Time (ns)')
plt.legend()

# Show grid
plt.grid(True, which="both", ls="--")

# Save the figure
plt.savefig('SelfBalancingExperiment.png')

# Show the plot
plt.show()

