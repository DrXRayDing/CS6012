import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# File path
file_path = 'CollisionDetectionExperimentResults.tsv'

# Load the data from the TSV file
# Assuming the TSV file does not have headers, you need to manually assign column names
data = pd.read_csv(file_path, sep='\t')

# Create a figure
plt.figure(figsize=(10, 6))

# Plot the data
plt.plot(data['Size'], data['OptimizedCollision'], marker='o', linestyle='-', label='OptimizedCollision')
#plt.loglog(data['Size'], data['NaiveCollision'], marker='o', linestyle='-', label='NaiveCollision')

# Set the title, labels, and legend of the plot
plt.title('Collision detection experiment for optimized approach')
plt.xlabel('Size')
plt.ylabel('Average Time (ns)')
plt.legend()

# Show grid
plt.grid(True, which="both", ls="--")

# Save the figure
plt.savefig('collisionOpt.png')

# Show the plot
plt.show()

