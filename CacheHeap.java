
public class CacheHeap 

	{

		public int size;
		public Node [] array_heap;
		public int curr_position;
		
		public CacheHeap(int num)
		
		{
			this.size=num;
			array_heap = new Node[num+3];
			
			curr_position = 0;
			
		}
		
		public void create_heap(Integer [] freq, Integer[] val)
		
		{
			if(freq.length>0)
			
			{
				for(int i=0;i<freq.length;i++)
				
				{
					Node node = new Node(freq[i], val[i]);
					insert(node);
				}
			}	
			
		}
		
		public void insert(Node data)
		
		{
			if(curr_position==0)
			{
				array_heap[curr_position+3]=data;
				curr_position = 4;
			}
			else
			{
				array_heap[curr_position++]=data;
				heapify();
			}
		}
		
		public void heapify()
		
		{
			int pos = curr_position-1;
			while(pos>2 && array_heap[(pos/4)+2]!=null && array_heap[(pos/4)+2].frequency>array_heap[pos].frequency)
			
			{
				Node temp = array_heap[pos];
				array_heap[pos]=array_heap[(pos/4)+2];
				array_heap[(pos/4)+2] = temp;
				pos = (pos/4)+2;
			}
		}
		
		public Node extract_min()
		
		{
			Node min=null;
			if(array_heap[3]!=null)
			
			{
				min = array_heap[3];
				array_heap[3]=array_heap[curr_position-1];
				array_heap[curr_position-1]=null;
				curr_position--;		
				heapify_down(3);
			}
			return min;
		}
		
		public void heapify_down(int index)
		
		{
			int smallest =index;
			if(4*(index-2)<curr_position && array_heap[smallest].frequency>array_heap[4*(index-2)].frequency)
			
			{
				smallest = 4*(index-2);
			}
			
			if(4*(index-2)+1<curr_position && array_heap[smallest].frequency>array_heap[4*(index-2)+1].frequency)
			{
				smallest = 4*(index-2)+1;
			}
			
			if(4*(index-2)+2<curr_position && array_heap[smallest].frequency>array_heap[4*(index-2)+2].frequency)
			{
				smallest = 4*(index-2)+2;
			}
			
			if(4*(index-2)+3<curr_position && array_heap[smallest].frequency>array_heap[4*(index-2)+3].frequency)
			
			{
				smallest = 4*(index-2)+3;
			}
			
			if(smallest!=index)
			
			{
				Node temp2 = array_heap[index];
				array_heap[index] = array_heap[smallest];
				array_heap[smallest] = temp2;
				
				heapify_down(smallest);
			}
					
		}
		
		
	
	
		Node build_cache_huffman()
		
		{
			Node parent=null;
			while(array_heap.length>1)
			
			{
				Node node1 = extract_min();
				Node node2 = extract_min();
				if(node1!=null && node2!=null)
				
				{
					int sum = node1.frequency + node2.frequency;
					parent = new Node(sum, 0);
					parent.left = node1;
					parent.right = node2;
					insert(parent);
				}
				else
				{
					break;
				}
			}
			return parent;
		}
	}
	