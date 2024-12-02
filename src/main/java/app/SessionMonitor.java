package app;

public class SessionMonitor extends Thread {

    @Override
    public void run() {
        while (true) {
            app.Session session = app.Session.getInstance();
            if (session.isUserLoggedIn()) {
                System.out.println("User is logged in:");
                System.out.println("Username: " + session.getUsername());
                System.out.println("Wins: " + session.getWin());
                System.out.println("Losses: " + session.getLose());
                System.out.println("History: " + session.getHistory());
            }
            else {
                System.out.println("No user is logged in.");
            }
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                System.err.println("Session monitor interrupted: " + e.getMessage());
            }
        }
    }
}
