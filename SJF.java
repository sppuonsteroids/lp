import java.util.*;

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int rt[] = new int[n];
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nPID: ");
            pid[i] = sc.nextInt();
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int completed = 0, time = 0;
        ArrayList<Integer> order = new ArrayList<>(); // store execution order

        while (completed != n) {
            int min = Integer.MAX_VALUE;
            int index = -1;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && rt[i] < min) {
                    min = rt[i];
                    index = i;
                }
            }

            if (index == -1) {
                time++;
                continue;
            }

            order.add(pid[index]); // record process execution
            rt[index]--;
            time++;

            if (rt[index] == 0) {
                completed++;
                ct[index] = time;
            }
        }

        // Calculating TAT and WT
        float tw = 0, tt = 0;
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
            tw += wt[i];
            tt += tat[i];
        }

        System.out.println("\nAverage Waiting Time = " + (tw / n));
        System.out.println("Average Turnaround Time = " + (tt / n));

        // Gantt Chart
        System.out.println("\nOrder of Execution (Gantt Chart):");
        System.out.print("| ");
        for (int i = 0; i < order.size(); i++) {
            System.out.print("P" + order.get(i) + " | ");
        }
        System.out.println();
    }
}
