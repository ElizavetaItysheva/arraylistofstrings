public class IntegerListimpl implements IntegerList {
// начальный пустой массив чисел
    private Integer [] integersArray;
    // размер листа по-умолчанию, равный 10
    private static final int DEFAULT_SIZE = 10;
    // размер листа
    private static int size;

    //конструктор без параметров, который создает массив на 10 элементов
    public IntegerListimpl(){
        this.integersArray = new Integer[DEFAULT_SIZE];
    }

    //конструктор, который создает массив указанной емкости
    public IntegerListimpl( int capacity){
        if (capacity >= 0){
            this.integersArray = new Integer[capacity];
        }
        else {
            throw new IllegalStateException("Некорректное значение размера!");
        }
    }


    @Override
    public Integer add( Integer item ) {
        // проверка на корректность числа
        isItemExist(item);
            //если в массиве места нет
            if (size == integersArray.length) {
                //увеличиваем массив с помощью доп.метода
                integersArray = increaseCapacity();
            }
            //записываем значение числа в конец списка
            integersArray[size] = item;
            // увеличиваем значение размера списка
            size++;
            // возвращаем добавленное число
            return item;
    }

    //дополнительный закрытый метод для увеличения размера массива
    private Integer[] increaseCapacity(){
        //создаем новый массив большего размера
        Integer[] temp = new Integer[(integersArray.length * 2)];
        //копируем в новый массив элементы из старого массива
        System.arraycopy(integersArray, 0, temp, 0, integersArray.length);
        //возвращаем новенький массив с теми же элементами и бОльшим размером
        return temp;
    }

    //проверяем индексы, не выходят ли они за границы массива
    private void isIndexExist( int index){
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("Элемент не найден. "
                    + "Размер массива = " + size
                    + ". Всего элементов в массиве = " + integersArray.length);
        }
    }
    // проверка числа на корректность
    private void isItemExist( Integer item){
        if(item == null){
            throw new NullPointerException("Число введено не корректно.");
        }
    }
    //метод для проверки на нехватку места при сдвиге вправо, если места нет, то расширяемся
    private void ensureCapacity(int min){
        if(min > integersArray.length){
            integersArray = increaseCapacity();
        }
    }
    @Override
    public Integer add( int index, Integer item ) {
        //проверка на корректность числа
        isItemExist(item);
        //проверяем подходит ли значение индекса по размеру массива
        isIndexExist(index);
        // проверка на вместительность при сдвиге элементов вправо
        ensureCapacity(size+1);
        // теперь смещаем все с помощью удобного метода
        System.arraycopy(integersArray, index, integersArray, index + 1, size - index);
        // приравниваем элемент к нужному индексу и прибавляем размер массива, возвращаем элемент
        integersArray[index] = item;
        size++;
        return item;
    }



    @Override
    public Integer set( int index, Integer item ) {
        // проверка на корректность числа
        isItemExist(item);
            //проверка на корректность индекса
            isIndexExist(index);
                //если все ок, то перезаписываем число и возвращаем его
                integersArray[index] = item;
                return item;
    }

    @Override
    public Integer remove( Integer item ) {
        //проверка параметра на корректность
        isItemExist(item);
            // проходимся по массиву
            for (int i = 0; i < size; i++) {
                //если значение нашлось, то удаляем элемент, возвращая его
                if (item.equals(integersArray[i])) {
                    remove(i);
                    }
                }
        return item;
            }


    @Override
    public Integer remove( int index ) {
        //проверка на корректность индекса
       isIndexExist(index);
            // проходимся по массиву от индекса до размера массива
            for (int i = index; i < size; i++) {
                //перезаписывам значения, смещая влево
                integersArray[i] = integersArray[i + 1];
            }
            return integersArray[index];
    }

    @Override
    public boolean contains( Integer item ) {
            //проверка параметра
        isItemExist(item);
        int min = 0;
        int max = size;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(integersArray[mid])) {
                return true;
            }

            if (item < integersArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }

        }
        return false;
    }

    @Override
    public int indexOf( Integer item ) {
        // проверка параметра
        isItemExist(item);
        // создаем переменную, куда запишем индекс
        int targetIndex;
        // проходимся по массиву
        for (int i = 0; i < size; i++) {
            // если элементы совпали, то запишем индекс и вернем его
          if(integersArray[i].equals(item)){
              targetIndex = i;
              return targetIndex;
          }
        }
        // если не нашли, вернем -1
        return -1;
    }

    @Override
    public int lastIndexOf( Integer item ) {
        // также проверяем параметр на корректность
        isItemExist(item);
        //создаем переменную для записи индекса
        int targetIndex;
        //проходимся по индексу, только от конца и если находим, то возвращаем индекс
        for (int i = size; i > 0; i--) {
            if(integersArray[i].equals(item)){
                targetIndex = i;
                return targetIndex;
            }
        }
        // если ничего не нашли, то возвращаем -1
        return -1;
    }

    @Override
    public Integer get( int index ) {
        // проверка индекса на корректность
        isIndexExist(index);
       return integersArray[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals( IntegerList otherList ) {
        Integer[] temp1 = otherList.toArray();

        boolean temp = true;
        if (!(temp1.length == size)) {
            temp = false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!(temp1[i].equals(integersArray[i]))) {
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
            integersArray[i] = null;
    }

    @Override
    public Integer[] toArray() {
        Integer[] temp = new Integer[size];
       System.arraycopy(integersArray, 0, temp, 0, size);

        return temp;
    }
    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
// как ни странно, этот метод сортировки оказался быстрее других
    public Integer[] sortMethodChoice(Integer[] integers){
        for (int i = 0; i < integers.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[j] < integers[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(integers, i, minElementIndex);
        }
        return integers;
    }
    public void show() {
        for (Integer elem: integersArray) {
            System.out.print(elem + " ");
        }
    }
}
