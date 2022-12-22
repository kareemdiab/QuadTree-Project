# QuadTree-Project
For this project I implemented a quad tree and a skiplist as a database for inserting, removing, and querying information about points with xy locations. The skiplist is used to find information about the point when requested by name, and the quadtree when requested by name. The quadtree will store each inserted point into their respective quadrant based off of their xy coordinates and the quadtree leaf nodes have splitting conditions: 
  When there are more than 3 points in the leaf node AND at least two of the points have different locations.
Leaf Nodes also merge back into one when these conditions are no longer met after a removal. This quadtree uses recursive implementation for traversals 
in querying, removing, etc. 
I use 'Internal Nodes' to simply hold pointers to each of its four child leaf nodes. 'Leaf Nodes' to hold up to three unique points, and 'flynodes' to represent empty nodes in order to avoid null pointer exceptions. 
The project reads from input file specified in command line and outputs to console confirmations of insertion, removal, and representations of quadtree and skiplist when requested in the input file. 

Invocation: 
     %> java Point2 {inputFile}
