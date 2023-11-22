import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# List of file paths
file_paths = ['LinkedListStack_pop.tsv', 'ArrayStack_pop.tsv']

# Create a figure
plt.figure(figsize=(10, 6))

# Loop through each file and plot the data
for file_path in file_paths:
    # Load the data from the TSV file
    data = pd.read_csv(file_path, header=None, sep='\t')  # No header in the file

    # Assign appropriate column names
    data.columns = ['Size', 'Average Time']

    # Plot the data
    plt.plot(data['Size'], data['Average Time'], marker='o', linestyle='-', label=file_path)

# Set the title, labels, and legend of the plot
plt.title('LinkedListStack vs ArrayStack for pop method')
plt.xlabel('Size')
plt.ylabel('Average Time (ns)')
plt.legend()

# Show grid
plt.grid(True, which="both", ls="--")

# Save the figure
plt.savefig('popMethod.png')

# Show the plot
#plt.show()
