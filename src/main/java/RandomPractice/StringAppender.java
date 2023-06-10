package RandomPractice;

public class StringAppender {
    private String data;

    public StringAppender() {
        this.data = "EXIST";
    }

    public String appendTwoStrings(String first, String second) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(2);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                data = concatString(data, first);
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                data = concatString(data, second);
            }
        };
        t1.start();
        t2.start();

        try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        return data;
    }

    public String concatString(String base, String suffix) {
//        String transformed =
        base = base + suffix;
        return base;
    }
}
