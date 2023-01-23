package adfer.springapp.SpringWebApp.bootstrap;

import adfer.springapp.SpringWebApp.repositories.GameRepository;
import adfer.springapp.SpringWebApp.repositories.PlatformRepository;
import adfer.springapp.SpringWebApp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;
    private final PublisherRepository publisherRepository;
    public BootStrapData(PlatformRepository platformRepository, GameRepository gameRepository, PublisherRepository publisherRepository) {
        this.platformRepository = platformRepository;
        this.gameRepository = gameRepository;
        this.publisherRepository = publisherRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        /*
        boolean flag = true;
        int option;
        Scanner sc = new Scanner(System.in);
        while(flag){
            System.out.println("Please, write in the number of one of the following operations:"+
                    "\n1. Add a new book"+"\n2. Add a new author"+"\n3. Add a new publisher");
            option=sc.nextInt();
            sc.nextLine();
             switch (option){
                 case 1:{
                     System.out.println("Please, write in the title of the book");
                     String title = sc.nextLine();
                     System.out.println("Please, write in the ISBN code of the book");
                     String isbn = sc.nextLine();
                     Book book = new Book(title,isbn);
                     Long book_id = 0L;

                     while(book_id >= 0){
                         System.out.println("Select one of the following authors: ");
                         platformRepository.findAll().forEach(author -> {System.out.println(author.getId()+"-"+author.getFirstName());});
                         book_id=sc.nextLong();
                         Optional<Author> book_author = platformRepository.findById(book_id);
                         if(book_author.isPresent()){
                             if(!book.getAuthors().contains(book_author.get())){ //check first if the book is already added
                                 book_author.get().getBooks().add(book);
                                 book.getAuthors().add(book_author.get());
                                 System.out.println("Author "+book_author.get().getFirstName()+" successfully added.");
                             }
                             else System.out.println("Author has been already added.");
                         }
                         else System.out.println("The specified author does not exist");
                     }
                     System.out.println("Please, write in the id of the following publishers: ");
                     publisherRepository.findAll().forEach(publisher -> {System.out.println(publisher.getId()+"-"+publisher.getName());});
                     book_id = sc.nextLong();
                     Optional<Publisher> publisher = publisherRepository.findById(book_id);
                     if(publisher.isPresent()){
                         publisher.get().getBooks().add(book);
                         book.setPublisher(publisher.get());
                     }
                     else System.out.println("The specified publisher does not exist");

                     gameRepository.save(book);
                     break;
                 }

                 case 2:{
                     System.out.println("Please, write in the name of the author");
                     String firstname = sc.nextLine();
                     System.out.println("Please, write in the last name of the author");
                     String lastname = sc.nextLine();
                     Author author = new Author(firstname,lastname);
                     platformRepository.save(author);
                     break;
                 }

                 case 3:{
                     System.out.println("Please, write in the name of the publisher");
                     String name = sc.nextLine();
                     System.out.println("Please, write in the address of the publisher (address,city,country,zipcode)");
                     String address = sc.nextLine();
                     String elements [] = address.split(",");
                     Publisher publisher = new Publisher(name,elements[0],elements[1],elements[2],Integer.parseInt(elements[3]));
                     publisherRepository.save(publisher);
                     break;
                 }
             }
        }
        */

    }
}
