import java.io.IOException;


public class encoder

	{
	

		public static void main(String[] args) 
		
		{
			Huffman_tree huffman = new Huffman_tree();
			Node huffRoot = null;
			
			try
			
			{
				String s = args[0];
				huffman.frequency_table(s);
			}
			catch (Exception e) 
			
			{
				e.printStackTrace();
			}
			
			Integer[] countArr = huffman.freq.toArray(new Integer[huffman.freq.size()]);
			Integer[] valueArr = huffman.val.toArray(new Integer[huffman.val.size()]);
			
			
			
			
				CacheHeap cacheHeap = new CacheHeap(huffman.freq.size());
				cacheHeap.create_heap(countArr, valueArr);
				huffRoot = cacheHeap.build_cache_huffman();
			
			
			
			
		
			huffman.build_codetable(huffRoot);
			
			try 
			{
				huffman.txtout();
				huffman.binout(args[0]);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		
	}
