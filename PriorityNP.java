import java.util.*;

public class PriorityNP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n],
        at[] = new int[n],
        bt[] = new int[n],
        pr[] = new int[n];
        int ct[] = new int[n],
        tat[] = new int[n],
        wt[] = new int[n];
        boolean done[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("PID AT BT PR: ");
            pid[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
        }

        int time = 0, completed = 0;
        ArrayList<Integer> order = new ArrayList<>();
        ArrayList<Integer> startTime = new ArrayList<>();
        ArrayList<Integer> endTime = new ArrayList<>();

        while (completed < n) {
            int idx = -1, minPr = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++)
                if (!done[i] && at[i] <= time && pr[i] < minPr) {
                    minPr = pr[i];
                    idx = i;
                }

            if (idx == -1) { time++; continue; }

            startTime.add(time);   // Gantt start
            time += bt[idx];
            endTime.add(time);     // Gantt end

            ct[idx] = time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            done[idx] = true;
            order.add(pid[idx]);
            completed++;
        }

        double avgWT = 0, avgTAT = 0;
        System.out.println("\nPID\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            avgWT += wt[i];
            avgTAT += tat[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        // 🟩 Gantt Chart Section (Added)
        System.out.println("\nGantt Chart:");
        for (int i = 0; i < order.size(); i++) {
            System.out.print("|  P" + order.get(i) + "  ");
        }
        System.out.println("|");

        for (int i = 0; i < startTime.size(); i++) {
            System.out.print(startTime.get(i) + "\t");
        }
        System.out.println(endTime.get(endTime.size() - 1));

        // Summary
        System.out.println("\nExecution Order: " + order);
        System.out.println("Average Waiting Time: " + avgWT / n);
        System.out.println("Average Turnaround Time: " + avgTAT / n);
    }
}
