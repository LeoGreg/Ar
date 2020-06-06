package am.basic.jdbcStart;


public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println(args instanceof Object);
//        CardRepository cardRepository= new CardRepository(new DataSource());
//        Card card= cardRepository.SELECT_BY_CLIENT_EMAIL("Davit.Pr.00@gmail.com");
//        Card card1=cardRepository.SELECT_BY_CLIENT_EMAIL("Grigor.9920@gmail.com");
//        new Thread(){
//            public void run(){
//                try {
//                    cardRepository.transfer(card,card1,5000);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        CardRepository cardRepository = new CardRepository(new DataSource());
//        Card transmitter = cardRepository.SELECT_BY_CLIENT_EMAIL("Davit.Pr.00@gmail.com");
////
////        Card receiver = cardRepository.SELECT_BY_CLIENT_EMAIL("Grigor.9920@gmail.com");
//        StudentRepository studentRepository = new StudentRepository(new DataSource());
//        Student student = studentRepository.getById(1);
//        Student student1 = studentRepository.getById(2);
//        Student student2 = studentRepository.getById(3);
//        Student student3 = studentRepository.getById(4);
//
//        BlockingQueue<Thread> queue = new SynchronousQueue<>();
//
//
//
//
//        queue.add(new Thread(() -> {
//            try {
//                studentRepository.transfer(student, student1, 50);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }));
//        queue.add(new Thread(() -> {
//            try {
//                studentRepository.transfer(student2, student3, 50);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }));
//        for (int i=0;i<queue.size();i++){
//            queue.poll();
//        }


//        User user= new User();
//        DataSource dataSource= new DataSource();
//        UserRepository userRepository= new UserRepository(dataSource);
//        user.setName("Jakab");
//        user.setSurname("Smith");user.setEmail("J@77.yahoo.com");user.setPassword("89uuuy#$5fdg");
//        user.setCode("6789");
//        user.setStatus(2);
//        userRepository.INSERT_INTO(user);
    }

}
//        DataSource dataSource = new DataSource();
//        StudentRepository studentRepository = new StudentRepository(new DataSource());
//        StudentRepository studentRepository1 = new StudentRepository(new DataSource());
//
//
//        Student studentFrom = studentRepository.getById(1);
//
//        Student studentInto = studentRepository.getById(2);
//        new Thread() {
//            public void run() {
//                try {
//                    studentRepository.transfer(studentFrom, studentInto, 150);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }.start();
//
//        System.out.println(studentRepository1.getById(1));
//        System.out.println(studentRepository1.getById(2));
//    }
//
//}


//
////        new Thread() {
////            public void run() {
////                try {
////                    studentRepository.withdraw(studentFrom, 200);
////                } catch (Exception e) {
////                    System.out.println(e);
////                }
////            }
////        }.start();
////        new Thread() {
////            public void run() {
////                try {
////                    studentRepository.deposit(studentFrom, 100);
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
////        }.start();
////        new Thread() {
////            public void run() {
////                try {
////                    studentRepository.transfer(studentRepository1.getById(1), studentInto, 150);
////                } catch (Exception e) {
////                    System.out.println(e);
////                }
////            }
////        }.start();
////
////        System.out.println(studentRepository1.getById(1));
////        System.out.println(studentRepository1.getById(2));
////    }
////
////    }
//    }
////    DataSource dataSource = new DataSource();
////        User user = new User();
////        UserRepository userRepository = new UserRepository(dataSource);
////        user.setName("Grigor");
////        user.setSurname("Martirosyan");
////        user.setEmail("grigor-martirosyan-1999@mail.ru");
////        user.setPassword("8989Ayu###");
////        user.setCode("2143");
////        user.setStatus(1);
////        userRepository.INSERT_INTO(user);
//
////        user.setCode("2222");
////        user.setStatus(1);
////        userRepository.UPDATE(user);
//
////        List list=userRepository.SEARCH_BY_ID_STATUS_EMAIL(1,1,"Grigor");
////        System.out.println(list);
//
//
////        System.out.println( userRepository.SELECT_ALL());
//
//
////    public static void main(String[] args) throws SQLException, ClassNotFoundException {
////        DataSource dataSource = new DataSource();
////        CardRepository cardRepository = new CardRepository(dataSource);
//
//
////    public static void main(String[] args) {
////        Card card = new Card();
////        card.setCard_number("55");
////        card.setExpiry("7878");
////        card.setCard_holder_name("90");
////        card.setClient_email("7897");
////        Card card1 = new Card();
////        card1.setCard_number("55");
////        card1.setExpiry("7878");
////        card1.setCard_holder_name("90");
////        card1.setClient_email("7897");
////        System.out.println(card.equals(card1));
////    }
//
//
////        card.setCard_holder_name("David");
////        card.setCard_number("2222_9099_5666_8888");
////        card.setExpiry("12/12/2024");
////        card.setClient_email("Davit.Pr.00@gmail.com");
////        cardRepository.INSERT_INTO(card);
//
//
////        cardRepository.INSERT_INTO(card);
//
//
////        Card card = cardRepository.SELECT_BY_CARD_HOLDER_NAME_AND_CARD_NUMBER("Grigor", "3333_4444_5555_1190");
////        System.out.println(card);
//
////
////        List<?> list = cardRepository.SELECT_ALL();
////        System.out.println(list+" ");
//
//
////        card = cardRepository.SELECT_BY_CLIENT_EMAIL("Grigor.9920@gmail.com");
////        System.out.println(card);
//
//
////        List<Card> list = cardRepository.SEARCH_BY_CARD_HOLDER_NAME_AND_CARD_NUMBER("av", "9099");
////        System.out.println(list);
//
////
////        card.setCard_number("2222_9099_5666_8889");
////        cardRepository.UPDATE_KEY_IS_EXPIRY(card);
//
//
//}