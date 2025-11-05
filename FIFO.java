import java.util.*; 
 
public class FIFO{ 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
 
        System.out.print("Enter number of frames: "); 
        int frames = sc.nextInt(); 
 
        System.out.print("Enter number of pages: "); 
        int n = sc.nextInt(); 
 
        int[] pages = new int[n]; 
        System.out.println("Enter page reference string:"); 
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt(); 
 
        int[] frame = new int[frames]; 
        Arrays.fill(frame, -1); 
 
        int pointer = 0, pageFaults = 0; 
 
        for (int page : pages) { 
            boolean found = false; 
 
            for (int f : frame) 
                if (f == page) { found = true; break; } 
 
            if (!found) { 
                frame[pointer] = page; 
                pointer = (pointer + 1) % frames; 
pageFaults++; 
} 
System.out.print("Frames: "); 
for (int f : frame) System.out.print((f == -1 ? "-" : f) + " "); 
System.out.println(); 
} 
System.out.println("\nTotal Page Faults = " + pageFaults); 
} 
}