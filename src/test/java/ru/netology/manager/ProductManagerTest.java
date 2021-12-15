package ru.netology.manager;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    public static final Book warAndPeace = new Book(1, "warAndPeace", 1550, "tolstoy");
    public static final Book moron = new Book(2, "moron", 760, "dostoevsky");
    public static final Book poetryCollection = new Book(3, "poetryCollection", 440, "yesenin");
    public static final Book eugeneOnegin = new Book(4, "eugeneOnegin", 880, "pushkin");
    public static final Book deadSouls = new Book(5, "deadSouls", 999, "gogol");
    public static final Smartphone galaxyA40 = new Smartphone(6, "galaxyA40", 16000, "samsung");
    public static final Smartphone iPhone13 = new Smartphone(7, "iPhone13", 54000, "apple");
    public static final Smartphone pocoX3pro = new Smartphone(8, "pocoX3pro", 17500, "xiaomi");
    public static final Smartphone a35 = new Smartphone(9, "a35", 7000, "nokia");
    public static final Smartphone galaxyA22 = new Smartphone(10, "galaxyA22", 35000, "samsung");
    public static final Product ruchka = new Product(3, "berligo", 34);


    ProductRepository repository = new ProductRepository();
    ProductManager catalog = new ProductManager(repository);

    @Test
    public void shouldAddBookToCatalog() {
        catalog.add(moron);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{moron};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddAndFindBook() {
        catalog.add(moron);
        Product[] actual = catalog.searchBy("moron");
        Product[] expected = new Product[]{moron};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByAuthorBook(){
        catalog.add(deadSouls);
        Product[] actual = catalog.searchBy("gogol");
        Product[] expected = new Product[]{deadSouls};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchBook(){
        catalog.add(warAndPeace);
        Product[] actual = catalog.searchBy("gogol");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
}

    @Test
    public void shouldSearchAddSmartphone(){
        catalog.add(galaxyA40);
        Product[] actual = catalog.searchBy("galaxyA40");
        Product[] expected = new Product[]{galaxyA40};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchManufacturer() {
        catalog.add(iPhone13);
        Product[] actual = catalog.searchBy("apple");
        Product[] expected = new Product[]{iPhone13};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchSmartphone(){
        catalog.add(galaxyA40);
        Product[] actual = catalog.searchBy("gogol");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchAnything(){
        catalog.add(ruchka);
        Product[] actual = catalog.searchBy("");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSmartphone(){
        catalog.add(galaxyA40);
        catalog.add(galaxyA22);
        catalog.add(a35);
        catalog.add(eugeneOnegin);
        Product[] actual = catalog.searchBy("galaxy");
        Product[] expected = new Product[]{galaxyA40,galaxyA22};
        assertArrayEquals(expected, actual);
    }

}


