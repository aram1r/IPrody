public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Alex", "Rogov", "Olegovich", "engineer", "any@mail.ru", "88005553555", 20_000, 34);
        employees[1] = new Employee("Snjezana", "Galkina", "Alexandrovna", "maid", "SnjezanaGalkina@teleworm.us", "078 3655 4704", 25_000, 44);
        employees[2] = new Employee("Mary", "Gerasimova", "Igorevna", "doctor", "MaryGerasimova@teleworm.us", "077 1059 2355", 55_000, 48);
        employees[3] = new Employee("Xenophon", "Abdulov", "Ramilevich", "driver", "XenophonAbdulov@teleworm.us", "079 1200 1093", 35_000, 64);
        employees[4] = new Employee("Daniel", "Degtyaryov", "Alekseevich", "technician", "DanielDegtyaryov@armyspy.com", "079 0367 7641", 45_000, 33);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge()>40) {
                System.out.println(employees[i].toString());
            }
        }
    }
}