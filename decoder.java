
public class decoder

	{
	
	
		public static void main(String[] args) 
		{
			Huffman_tree huffman = new Huffman_tree();
			huffman.build_decode_tree(args[1],args[0]);
			
		}
		
	}
