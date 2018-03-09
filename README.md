# Huffman-Encoder-Decoder
In computer science and information theory, a Huffman code is a particular type of optimal prefix code that is commonly used for lossless data compression. Huffman coding uses a specific method for choosing the representation for each symbol, resulting in a prefix code. The output from Huffman's algorithm can be viewed as a variable-length code table for encoding a source symbol. 

# Modules
The project consists of an encoder built on a 4-way cache optimized heap and a decoder. The encoder takes a text file of data as input and produces a code table and encoded binary file as output. The decoder does the reverse process and generates the huffman tree from the code table and uses this tree to decode the encoded binary file into the original output text file. 

# Extra
The encoder can also be built using a cache heap which is marginally slower than the 4-way cache optimized heap, however it may be preferred over depending on the applications and type of input data. 
