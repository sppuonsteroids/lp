import java.util.*; 
 
public class LRU { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
 
        System.out.print("Enter number of frames: "); 
        int frames = sc.nextInt(); 
 
        System.out.print("Enter number of pages: "); 
        int n = sc.nextInt(); 
 
        int[] pages = new int[n]; 
        System.out.println("Enter page reference string:"); 
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt(); 
 
        ArrayList<Integer> frame = new ArrayList<>(frames); 
        int pageFaults = 0; 
 
        for (int i = 0; i < n; i++) { 
            int page = pages[i]; 
 
            if (!frame.contains(page)) { 
                if (frame.size() == frames) { 
                    int lru = Integer.MAX_VALUE, indexToRemove = -1; 
                    for (int f = 0; f < frame.size(); f++) { 
                        int lastUsed = -1; 
                        for (int j = i - 1; j >= 0; j--) { 
                            if (pages[j] == frame.get(f)) { lastUsed = j; break; } 
                        } 
                        if (lastUsed < lru) { 
                            lru = lastUsed; 
                            indexToRemove = f; 
                        } 
                    } 
                    frame.set(indexToRemove, page); 
                } else { 
                    frame.add(page); 
                } 
                pageFaults++; 
            } 
 
            System.out.print("Frames: "); 
            for (int f = 0; f < frames; f++) { 
                if (f < frame.size()) System.out.print(frame.get(f) + " "); 
                else System.out.print("- "); 
            } 
            System.out.println(); 
        } 
 
        System.out.println("\nTotal Page Faults = " + pageFaults); 
    } 
} 
