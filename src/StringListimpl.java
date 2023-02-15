import java.util.*;

public class StringListimpl implements StringList{
// начальный пустой массив строк
    private String [] stringArray;
    // размер листа по-умолчанию, равный 10
    private static final int DEFAULT_SIZE = 10;
    // размер листа
    private static int size;

    //конструктор без параметров, который создает массив на 10 элементов
    public StringListimpl(){
        this.stringArray = new String[DEFAULT_SIZE];
    }

    //конструктор, который создает массив указанной емкости
    public StringListimpl(int capacity){
        if (capacity >= 0){
            this.stringArray = new String[capacity];
        }
        else {
            throw new IllegalStateException("Некорректное значение размера!");
        }
    }


    @Override
    public String add( String item ) {
        // проверка на корректность строки
        isItemExist(item);
            //если в массиве места нет
            if (size == stringArray.length) {
                //увеличиваем массив с помощью доп.метода
                stringArray = increaseCapacity();
            }
            //записываем значение строки в конец списка
            stringArray[size] = item;
            // увеличиваем значение размера списка
            size++;
            // возвращаем добавленную строку
            return item;
    }

    //дополнительный закрытый метод для увеличения размера массива
    private String[] increaseCapacity(){
        //создаем новый массив большего размера
        String[] temp = new String[(stringArray.length * 2)];
        //копируем в новый массив элементы из старого массива
        System.arraycopy(stringArray, 0, temp, 0, stringArray.length);
        //возвращаем новенький массив с теми же элементами и бОльшим размером
        return temp;
    }

    //проверяем индексы, не выходят ли они за границы массива
    private void isIndexExist( int index){
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("Элемент не найден. "
                    + "Размер массива = " + size
                    + ". Всего элементов в массиве = " + stringArray.length);
        }
    }
    // проверка строки на корректность
    private void isItemExist( String item){
        if(item == null || item.isEmpty()){
            throw new NullPointerException("Строка введена не корректно.");
        }
    }
    //метод для проверки на нехватку места при сдвиге вправо, если места нет, то расширяемся
    private void ensureCapacity(int min){
        if(min > stringArray.length){
            stringArray = increaseCapacity();
        }
    }
    @Override
    public String add( int index, String item ) {
        //проверка на корректность строки
        isItemExist(item);
        //проверяем подходит ли значение индекса по размеру массива
        isIndexExist(index);
        // проверка на вместительность при сдвиге элементов вправо
        ensureCapacity(size+1);
        // теперь смещаем все с помощью удобного метода
        System.arraycopy(stringArray, index, stringArray, index + 1, size - index);
        // приравниваем элемент к нужному индексу и прибавляем размер массива, возвращаем элемент
        stringArray[index] = item;
        size++;
        return item;
    }



    @Override
    public String set( int index, String item ) {
        // проверка на корректность строки
        isItemExist(item);
            //проверка на корректность индекса
            isIndexExist(index);
                //если все ок, то перезаписываем строку и возвращаем её
                stringArray[index] = item;
                return item;
    }

    @Override
    public String remove( String item ) {
        //проверка параметра на корректность
        isItemExist(item);
            // проходимся по массиву
            for (int i = 0; i < size; i++) {
                //если значение нашлось, то удаляем элемент, возвращая его
                if (item.equals(stringArray[i])) {
                    remove(i);
                    }
                }
        return item;
            }


    @Override
    public String remove( int index ) {
        //проверка на корректность индекса
       isIndexExist(index);
            // проходимся по массиву от индекса до размера массива
            for (int i = index; i < size; i++) {
                //перезаписывам значения, смещая влево
                stringArray[i] = stringArray[i + 1];
            }
            return stringArray[index];
    }

    @Override
    public boolean contains( String item ) {
            //проверка параметра
        isItemExist(item);
        //создаем локальную лог-переменную
        boolean temp = false;
        // проходимся по массиву
        for (String s : stringArray) {
            // если элементы совпали, то поменяем значение на тру и дальше не пойдем
            if (s.equals(item)) {
                temp = true;
                break;
            } else {
                return false;
            }
        }
        return temp;
    }

    @Override
    public int indexOf( String item ) {
        // проверка параметра
        isItemExist(item);
        // создаем переменную, куда запишем индекс
        int targetIndex;
        // проходимся по массиву
        for (int i = 0; i < size; i++) {
            // если элементы совпали, то запишем индекс и вернем его
          if(stringArray[i].equals(item)){
              targetIndex = i;
              return targetIndex;
          }
        }
        // если не нашли, вернем -1
        return -1;
    }

    @Override
    public int lastIndexOf( String item ) {
        // также проверяем параметр на корректность
        isItemExist(item);
        //создаем переменную для записи индекса
        int targetIndex;
        //проходимся по индексу, только от конца и если находим, то возвращаем индекс
        for (int i = size; i > 0; i--) {
            if(stringArray[i].equals(item)){
                targetIndex = i;
                return targetIndex;
            }
        }
        // если ничего не нашли, то возвращаем -1
        return -1;
    }

    @Override
    public String get( int index ) {
        // проверка индекса на корректность
        isIndexExist(index);
       return stringArray[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals( StringList otherList ) {
        String[] temp1 = otherList.toArray();

        boolean temp = true;
        if (!(temp1.length == size)) {
            temp = false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!(temp1[i+1].equals(stringArray[i+1]))) {
                    temp = false;
                    break;
                }
            }
        }
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int to = size, i = size = 0; i < to; i++)
            stringArray[i] = null;
    }

    @Override
    public String[] toArray() {
        String[] temp = new String[]{};
        if (size >= 0) System.arraycopy(stringArray, 0, temp, 0, size);
        return temp;
    }


    public void show() {
        for (String elem: stringArray) {
            System.out.print(elem + " ");
        }
    }
}
