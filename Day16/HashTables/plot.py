import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# File path
file_path = 'AddExperimentResults.tsv'

# Load the data from the TSV file
# Assuming the TSV file does not have headers, you need to manually assign column names
data = pd.read_csv(file_path,  sep='\t')

# Create a figure
plt.figure(figsize=(10, 6))

# Plot the data
#plt.plot(data['Size'], data['BadHashFunctor'], marker='o', linestyle='-', label='BadHashFunctor')
plt.plot(data['Size'], data['MediocreHashFunctor'], marker='o', linestyle='-', label='MediocreHashFunctor')
plt.plot(data['Size'], data['GoodHashFunctor'], marker='o', linestyle='-', label='GoodHashFunctor')

# Set the title, labels, and legend of the plot
plt.title('Add method test of three hash functors')
plt.xlabel('Size')
plt.ylabel('Average Time (ns)')
plt.legend()

# Show grid
plt.grid(True, which="both", ls="--")

# Save the figure
plt.savefig('AddExperiment2.png')

# Show the plot
plt.show()

