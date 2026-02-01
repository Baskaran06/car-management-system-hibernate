package carCrudHibernate;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CarCrud {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("scott");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean flag = true;

		while (flag) {
			System.out.println("1. Save Car Details \n2. Fetch All Car Details \n3. Fetch Car Detail Based on ID \n4. Remove Car Detail \n5. Update Car Detail \n6. Exit \n");
			System.out.println("Enter the Option to Perform :");
			int option = scanner.nextInt();

			switch (option) {

			case 1:
				Car car = new Car();
				System.out.println("Enter Car ID:");
				int Saveid = scanner.nextInt();
				car.setId(Saveid);

				System.out.println("Enter Car Brand:");
				String Savebrand = scanner.next();
				car.setBrand(Savebrand);

				System.out.println("Enter Car Price:");
				Double Saveprice = scanner.nextDouble();
				car.setPrice(Saveprice);

				entityTransaction.begin();
				entityManager.persist(car);
				entityTransaction.commit();
				System.out.println("Car details saved successfully.\n");
				break;

			case 2:
				Query query = entityManager.createQuery("select car from Car car");
				List<Car> cars = query.getResultList();

				for (Car elements : cars) {
					System.out.println("Car ID:" + elements.getId());
					System.out.println("Car Brand:" + elements.getBrand());
					System.out.println("Car Price:" + elements.getPrice());

				}
				System.out.println("Car details fetched successfully.\n");
				break;

			case 3:
				System.out.println("\nEnter the ID to fetch:");
				int Fetchid = scanner.nextInt();
				Car car1 = entityManager.find(Car.class, Fetchid);

				if (car1 != null) {
					System.out.println("Car ID:" + car1.getId());
					System.out.println("Car Brand:" + car1.getBrand());
					System.out.println("Car Price:" + car1.getPrice());
				}
				System.out.println("Car details fetched successfully for ID:" + Fetchid + ".\n");
				break;

			case 4:
				System.out.println("\nEnter the ID to delete:");
				int Deleteid = scanner.nextInt();
				Car car2 = entityManager.find(Car.class, Deleteid);

				if (car2 != null) {
					entityTransaction.begin();
					entityManager.remove(car2);
					entityTransaction.commit();
				}
				System.out.println("Car ID:" + Deleteid + "deleted successfully.\n");
				break;

			case 5:
				System.out.println("\nEnter the ID to update:");
				int Updateid = scanner.nextInt();
				Car car3 = entityManager.find(Car.class, Updateid);

				if (car3 != null) {
					System.out.println("\nEnter the new brand:");
					String Updatebrand = scanner.next();
					car3.setBrand(Updatebrand);
					entityTransaction.begin();
					entityManager.merge(car3);
					entityTransaction.commit();
				}
				System.out.println("Car details updated successfully.\n");
				break;

			case 6:
				flag = false;
				System.out.println("Exited successfully.");
			}
		}
		scanner.close();
		entityManager.close();
		entityManagerFactory.close();
	}

}
