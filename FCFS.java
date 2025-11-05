import java.util.*; 
 
class FCFS { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
 
        System.out.print("Enter number of processes: "); 
        int n = sc.nextInt(); 
 
        int pid[] = new int[n], at[] = new int[n], bt[] = new int[n]; 
        int wt[] = new int[n], tat[] = new int[n], ct[] = new int[n]; 
 
        for(int i=0;i<n;i++){ 
            System.out.print("Process ID: "); 
            pid[i] = sc.nextInt(); 
            System.out.print("Arrival Time: "); 
            at[i] = sc.nextInt(); 
            System.out.print("Burst Time: "); 
            bt[i] = sc.nextInt(); 
            System.out.println(); 
        } 
 
        // Sort by Arrival Time 
        for(int i=0;i<n-1;i++){ 
            for(int j=i+1;j<n;j++){ 
                if(at[i] > at[j]) { 
                    int t=at[i]; at[i]=at[j]; at[j]=t; 
                    t=bt[i]; bt[i]=bt[j]; bt[j]=t; 
                    t=pid[i]; pid[i]=pid[j]; pid[j]=t; 
                } 
            } 
        } 
 
        // Calculation 
        int time = 0; 
        double avgWT = 0, avgTAT = 0; 
 
        for(int i=0;i<n;i++){ 
            if(time < at[i]) time = at[i]; // CPU Idle case 
            time += bt[i]; 
            ct[i] = time; 
            tat[i] = ct[i] - at[i]; 
            wt[i] = tat[i] - bt[i]; 
            avgWT += wt[i]; 
            avgTAT += tat[i]; 
        } 
 
        // Output 
        System.out.println("\nPID\tAT\tBT\tWT\tTAT\tCT"); 
        for(int i=0;i<n;i++){ 
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+wt[i]+"\t"+tat[i]+"\t"+ct[i]); 
        } 
 
        System.out.println("\nGantt Chart:"); 
        System.out.print("|"); 
        for(int i=0;i<n;i++) System.out.print(" P"+pid[i]+" |"); 
        System.out.println(); 
 
        System.out.println("\nAverage Waiting Time: " + avgWT/n); 
        System.out.println("Average Turnaround Time: " + avgTAT/n); 
    } } 
