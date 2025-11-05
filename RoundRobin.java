import java.util.*;

public class RoundRobin{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n], at[] = new int[n], bt[] = new int[n], rt[] = new int[n];
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        int time = 0, completed = 0;
        int wt[] = new int[n], tat[] = new int[n], ct[] = new int[n];
        Queue<Integer> q = new LinkedList<>();
        boolean inQueue[] = new boolean[n];
        ArrayList<String> gantt = new ArrayList<>();

        while (completed < n) {
            for (int i = 0; i < n; i++)
                if (at[i] <= time && !inQueue[i] && rt[i] > 0) {
                    q.add(i);
                    inQueue[i] = true;
                }

            if (q.isEmpty()) { time++; continue; }

            int i = q.poll();
            gantt.add("P" + pid[i]);

            int t = Math.min(tq, rt[i]);
            rt[i] -= t;
            time += t;

            for (int j = 0; j < n; j++)
                if (at[j] <= time && !inQueue[j] && rt[j] > 0) {
                    q.add(j);
                    inQueue[j] = true;
                }

            if (rt[i] > 0)
                q.add(i);
            else {
                completed++;
                ct[i] = time;
                tat[i] = ct[i] - at[i];
                wt[i] = tat[i] - bt[i];
            }
        }

        System.out.println("\nGantt Chart:");
        for (String p : gantt) System.out.print("| " + p + " ");
        System.out.println("|\n");

        System.out.println("PID\tAT\tBT\tCT\tWT\tTAT");
        float avgWT = 0, avgTAT = 0;
        for (int i = 0; i < n; i++) {
            avgWT += wt[i];
            avgTAT += tat[i];
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + wt[i] + "\t" + tat[i]);
        }

        System.out.println("\nAverage WT = " + avgWT / n);
        System.out.println("Average TAT = " + avgTAT / n);
    }
}
