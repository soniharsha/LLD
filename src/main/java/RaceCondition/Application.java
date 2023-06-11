package RaceCondition;

public class Application {

    public static void main(String[] args) {
        FundTransferService fundTransferService = new FundTransferSynchronized();
        new Thread(()-> {
            fundTransferService.addBalance('A', 1000);
        }).start();
        new Thread(()-> {
            fundTransferService.addBalance('A', 2000);
        }).start();
        new Thread(()-> {
            fundTransferService.addBalance('B', 600);
        }).start();
        System.out.println(fundTransferService.fetchBalance('A'));
        System.out.println(fundTransferService.fetchBalance('B'));
        new Thread(()-> {
            fundTransferService.addBalance('B', 600);
        }).start();
        new Thread(()-> {
            fundTransferService.transfer('A','B', 600);
        }).start();
        new Thread(()-> {
            fundTransferService.transfer('B','A', 1000);
        }).start();
        new Thread(()-> {
            fundTransferService.transfer('A','B', 600);
        }).start();
        new Thread(()-> {
            fundTransferService.transfer('A','B', 600);
        }).start();

        try {
            Thread.sleep(4000);
        }catch (InterruptedException ie) {

        }
        System.out.println(fundTransferService.fetchBalance('A'));
        System.out.println(fundTransferService.fetchBalance('B'));
    }
}
