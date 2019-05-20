## IO

+ [1. Расскажите общее идею что такое поток ввода-вывода?](#Расскажите-общее-идею-что-такое-поток-ввода-вывода)
+ [2. Что такое Java IO?](#Что-такое-Java-IO)
+ [3. Что такое Java NIO?](#Что-такое-Java-NIO)
+ [4. Что такое Scanner?](#Что-такое-Scanner)
+ [5. Как работает Scanner внутри?](#Как-работает-Scanner-внутри)
+ [6. Какие базовые методы существуют в Scanner?](#Какие-базовые-методы-существуют-в-Scanner)
+ [7. Что такое байтовый поток? Как он реализован внутри?](#Что-такое-байтовый-поток-Как-он-реализован-внутри)
+ [8. Что такое символьный поток? Как он реализован внутри?](#Что-такое-символьный-поток-Как-он-реализован-внутри)
+ [9. Что такое буферизированный поток?](#Что-такое-буферизированный-поток)
+ [10. Что такое форматированный вывод? Какие механизмы позволяют осуществить форматированный вызов?](#Что-такое-форматированный-вывод-Какие-механизмы-позволяют-осуществить-форматированный-вызов)
+ [11. Как осуществлятся ввод и вывод из командной строки?](#Как-осуществлятся-ввод-и-вывод-из-командной-строки)
+ [12. Что такое класс Console? Расскажите его АПИ.](#Что-такое-класс-Console-Расскажите-его-АПИ)
+ [13. Что такое поток данных? Data stream.](#Что-такое-поток-данных-Data-stream)
+ [14. Что такое поток объектов, Object stream.](#Что-такое-поток-объектов-Object-stream)
+ [15. Что такое Path? Как он реализуется на разных ОС?](#Что-такое-Path-Как-он-реализуется-на-разных-ОС)
+ [16. Как получить список файлов?](#Как-получить-список-файлов)
+ [17. Как проверить что файловая сущность является файлом или папкой?](#Как-проверить-что-файловая-сущность-является-файлом-или-папкой)
+ [18. Как удалить файл?](#Как-удалить-файл)
+ [19. Как переместить файл?](#Как-переместить-файл)
+ [20. Как управлять атрибутами файла?](#Как-управлять-атрибутами-файла)
+ [21. Как создать файл?](#Как-создать-файл)
+ [22. Как создать директорию?](#Как-создать-директорию)
+ [23. Как записать в файл?](#Как-записать-в-файл)
+ [24. Как прочитать данные из файла?](#Как-прочитать-данные-из-файла)


## Расскажите общее идею что такое поток ввода вывода

Цель создания InputStream и OutputStream это абстрактный доступ к вводу и выводу. Источник при этом не важен. 
Это может быть файл, консоль, веб-страница. Stream - это бесконечный поток данных, подключенный к источнику данных.

[к оглавлению](#IO)

## Что такое Java IO

IO API – (Input & Output) в первую очередь это Java API, которые облегчают работу с потоками. 
В java.io существуют так называемые потоки ввода и вывода (InputStream and OutputStream).

В основном java.io предназначен для чтения и записи данных в ресурс:

1) файл;
2) при работе с сетевым подключением;
3) System.err, System.in, System.out;
4) при работе с буфером.

![Иерархия](https://github.com/a11exe/job4j/blob/master/interview_questions/io_diagram.gif)

Для разных типов данных существуют разные реализации классов


|_| Byte Based| _| Character Based| _ |
| ---| ---| ---| ---| --- |
| _| Input| Output| Input| Output |
| Basic| InputStream| OutputStream| Reader / InputStreamReader| Writer / OutputStreamWriter |
| Arrays| ByteArrayInputStream| ByteArrayOutputStream| CharArrayReader| CharArrayWriter |
| Files| FileInputStream / RandomAccessFile| FileOutputStream / RandomAccessFile| FileReader| FileWriter |
| Pipes| PipedInputStream| PipedOutputStream| PipedReader| PipedWriter |
| Buffering| BufferedInputStream| BufferedOutputStream| BufferedReader| BufferedWriter |
| Filtering| FilterInputStream| FilterOutputStream| FilterReader| FilterWriter |
| Parsing| PushbackInputStream / StreamTokenizer| _| PushbackReader / LineNumberReader| _ |
| Strings| _| _| StringReader| StringWriter |
| Data| DataInputStream| DataOutputStream| _| _ |
| Data - Formatted| _| PrintStream| _| PrintWriter |
| Objects| ObjectInputStream| ObjectOutputStream| _| _ |
 	 	

**Классы Java IO API**

**Базовые**

+ InputStream / OutputStream - абстрактный класс, определяющий потоковый байтовый ввод/вывод
+ Reader / Writer - Символьные потоки имеют два основных абстрактных класса Reader и Writer, управляющие потоками символов Unicode.
+ InputStreamReader / OutputStreamWriter Входной/выдодной поток, транслирующий байты в символы

**Массивы**

+ ByteArrayInputStream / ByteArrayOutputStream - использует байтовый массив в потоке.
+ CharArrayReader / CharArrayWriter - читает/пишет из символьного массива.

**Files**

+ FileInputStream / FileOutputStream - Чтение/Отправка данных в файл на диске. Реализация класса OutputStream                                       
+ RandomAccessFile / RandomAccessFile - Чтение/запись файлов с произвольным доступом. метод seek() позволяет переместиться к определенной позиции и изменить хранящееся там значение. 
При использовании RandomAccessFile необходимо знать структуру файла. Класс RandomAccessFile содержит методы для чтения и записи примитивов и строк UTF-8.
RandomAccessFile может открываться в режиме чтения ("r") или чтения/записи ("rw"). Также есть режим "rws", когда файл открывается для операций чтения-записи и каждое изменение данных файла немедленно записывается на физическое устройство.
+ FileReader / FileWriter FileWriter записывает данные в файл. При вводе/выводе практически всегда применяется буферизация, поэтому используется BufferedWriter.                           
Когда данные входного потока исчерпываются, метод readLine() возвращает null. Для потока явно вызывается метод close(); если не вызвать его для всех выходных файловых потоков, в буферах могут остаться данные, и файл получится неполным

**Буферизация**

+ BufferedInputStream / BufferedOutputStream  - буферизируемый поток. Буферы вывода нужно для повышения производительности
+ BufferedReader / BufferedWriter

[к оглавлению](#IO)

## Что такое Java NIO

| IO| NIO |
| ---| --- |
| Потокоориентированный| Буфер-ориентированный |
| Блокирующий (синхронный) ввод/вывод| Неблокирующий (асинхронный) ввод/вывод |

Состоит из 3 основных компонентов

+ Channels
+ Buffers
+ Selectors

Java NIO: Channels read data into Buffers, and Buffers write data into Channels
There are several Channel and Buffer types. Here is a list of the primary Channel implementations in Java NIO:

+ FileChannel
+ DatagramChannel
+ SocketChannel
+ ServerSocketChannel

A Selector allows a single thread to handle multiple Channel's. 
This is handy if your application has many connections (Channels) open, but only has low traffic on each connection. 
For instance, in a chat server.

Example.
```java
RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
    FileChannel inChannel = aFile.getChannel();

    ByteBuffer buf = ByteBuffer.allocate(48);

    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {

      System.out.println("Read " + bytesRead);
      buf.flip();

      while(buf.hasRemaining()){
          System.out.print((char) buf.get());
      }

      buf.clear();
      bytesRead = inChannel.read(buf);
    }
    aFile.close();
```

[к оглавлению](#IO)

## Что такое Scanner

Scanner класс в java.util для чтение данных примитивных типов int, double, string и т.д. Это самый простой способ для получения
входящих данных, но не самый эффективный если есть ограничения по времени.
Обычно создается в виде
```java
Scanner sc = new Scanner(System.in);
```
Для чтения данных типа XYZ используется метод nextXYZ(). Для проверки что есть данные такого типа hasNextXYZ()
Например:
+ hasNextBoolean()
+ hasNextByte()
+ hasNextDouble()
+ hasNextFloat()

[к оглавлению](#IO)

## Как работает Scanner внутри

использует регулярные выражения
```java
private static final String LINE_SEPARATOR_PATTERN =
                                           "\r\n|[\n\r\u2028\u2029\u0085]";
 private static Pattern NON_ASCII_DIGIT = Pattern.compile(
        "[\\p{javaDigit}&&[^0-9]]");
```                                           
кеширует значения
```java
public String nextLine() {
        modCount++;
        if (hasNextPattern == linePattern())
            return getCachedResult();
        clearCaches();

        String result = findWithinHorizon(linePattern, 0);
        if (result == null)
            throw new NoSuchElementException("No line found");
        MatchResult mr = this.match();
        String lineSep = mr.group(1);
        if (lineSep != null)
            result = result.substring(0, result.length() - lineSep.length());
        if (result == null)
            throw new NoSuchElementException();
        else
            return result;
    }
```  
Java Scanner class extends Object class and implements Iterator and Closeable interfaces.

[к оглавлению](#IO)

## Какие базовые методы существуют в Scanner

+ delimiter()	It is used to get the Pattern which the Scanner class is currently using to match delimiters.

+ hasNextLine 
+ nextLine 
+ hasNextInt 
+ nextInt ...

[к оглавлению](#IO)

## Что такое байтовый поток. Как он реализован внутри

Byte streams работает с данными побайтово (8 bits). Например, FileInputStream используется для чтения и FileOutputStream для записи.
Byte streams интерфейс, который внутри основан на байтовом массиве. В основе находится некий буфер который заполняется, вычитывается и заново заполняется.
Методы внутри native.
```java
 private native int read0() throws IOException;
``` 

[к оглавлению](#IO)

## Что такое символьный поток. Как он реализован внутри.

В Java, символы хранятся в кодировке Unicode (16 bit). Символный поток позволяет читать данные символ за символом. Для пример FileReader и FileWriter символьные потоки.
Можно задать свою кодировку
```java 
Reader reader = new InputStreamReader(in, "UTF-8");
``` 

[к оглавлению](#IO)

## Что такое буферизированный поток.

Для оптимизации операций ввода-вывода используются буферизуемые потоки. Эти потоки добавляют к стандартным специальный буфер в памяти, с помощью которого повышается производительность при чтении и записи потоков.
BufferedInputStream и BufferedOutputStream. 
Это может сделать программу намного эффективней, так как каждый такой запрос часто инициировал доступ к диску, сетевое действие, или некоторую другую работу, которая относительно дорога.

[к оглавлению](#IO)

## Что такое форматированный вывод. Какие механизмы позволяют осуществить форматированный вызов.

+ %a	Шестнадцатеричное число с плавающей точкой
+ %b	Булево значение
+ %c	Символ
+ %d	Десятичное целое
+ %e	Число в научной записи
+ %f	Десятичное число с плавающей точкой
+ %h	Хеш-код от аргумента
+ %o	Восьмеричное целое
+ %n	Символ переноса строки
+ %t	Время
+ %x	Шестнадцатеричное целое

String output = String.format("%s = %d", "joe", 35);
String.format(); Formatter

Можно создать Formatter и привязать его к StrungBuilder

```java
StringBuilder sbuf = new StringBuilder();
Formatter fmt = new Formatter(sbuf);
fmt.format("PI = %f%n", Math.PI);
System.out.print(sbuf.toString());
```
Также есть свое форматирование для вывода дат.

Можно задавать выравнивание, количество отступов.
```java
String.format("|%-20d|", 93); // prints: |93                  |

String.format("|%020d|", 93); // prints: |00000000000000000093|
```

[к оглавлению](#IO)

## Как осуществлятся ввод и вывод из командной строки.

По умолчанию ввод с клавиатуры, вывод на монитор.

Класс System содержит также три переменные предопределенных потоков ввода-вывода: in, out и err    
+ Переменная System.out ссылается на стандартный поток вывода. По умолчанию это консоль.
+ Переменная System.in ссылается на стандартный поток ввода, которым по умолчанию является клавиатура.
+ System.err - для ошибок.

[к оглавлению](#IO)

## Что такое класс Console Расскажите его АПИ

Альтернатива стандратным потокам ввода / вывода класс Console. Часто используются дл

Для создание экземпляра используется System.console(). Метод может вернуть NULL если консоль недоступна. 
Консоль позволяет вводить пароль используя метод readPassword (не видны символы при вводе, не сохраняется в памяти). 
   
+ flush(): выводит на консоль все данные из буфера
+ format(): выводит на консоль строку с использованием форматирования
+ printf(): выводит на консоль строку с использованием форматирования (фактически то же самое, что и предыдущий метод)
+ String readLine(): считывает с консоли введенную пользователем строку
+ char[] readPassword(): считывает с консоли введенную пользователем строку, при этом символы строки не отображаются на консоли

[к оглавлению](#IO)

## Что такое поток данных Data stream.

Классы DataOutputStream и DataInputStream позволяют записывать и считывать данные примитивных типов (boolean, char, byte, short, int, long, float, and double).
+ DataOutputStream.writeDouble	
+ DataInputStream.readDouble

[к оглавлению](#IO)

## Что такое поток объектов Object stream

ObjectOutputStream используется для конвертации объектов в поток. В java это называется сериализация. Объект преобразоыванный таким образом
может быть сохранен в базу данных, передан по сети и т.п. Для записи в файл можно использовать FileOutputStream.
Объект который передается в потоке должен реализовывать интерфейс java.io.Serializable.

```java
FileOutputStream fos = new FileOutputStream("EmployeeObject.ser");
ObjectOutputStream oos = new ObjectOutputStream(fos);
// write object to file
oos.writeObject(emp);
```

При сериализации используют переменную SerialVersionUID. 
Во время сериализации, среда выполнения Java создает номер версии для класса, так что она может десереализировать его позже. В Java этот номер версии известен как SerialVersionUID. Если во время десериализации, SerialVersionUID не соответствует, то процесс завершится с исключением

SerialVersionUID используется для указании версии сериализованных данных.

+ Когда мы не объявляем SerialVersionUID в нашем классе, среда выполнения Java делает это за нас, но этот процесс чувствителен ко многим метаданным класса включая количество полей, тип полей, модификаторы доступа полей, интерфейсов, которые реализованы в классе и пр. Вы можете найти точную информацию в документации о сериализации от Oracle.
+ Рекомендуется объявлять SerialVersionUID как private static final long переменную во избежание механизма по умолчанию. 

[к оглавлению](#IO)

## Что такое Path. Как он реализуется на разных ОС.

Java 7 представляет новую абстракцию для пути, а именно интерфейс Path. Он используется в новых функциях и API, по всему NIO.2. 
Объект пути содержит имена каталогов и файлов, которые составляют полный путь до файла/каталога, представленного объектом Path; 
Path содержит методы для извлечения элементов пути, манипуляций с ними и их добавления.

Путь к файлу, в разных системх может записываться по разному, \ или / поэтому лучше
использовать File.separator для построения пути

```java
// Cоздание объекта Path через вызов статического метода get() класса Paths 
Path testFilePath = Paths.get("/home/heorhi/testfile.txt"); 
         
//Пример строки создания объекта Path пути для запуска в Windows 
Path testFilePath = Paths.get("D:\\test\\testfile.txt");
``` 

[к оглавлению](#IO)

## Как получить список файлов.

```java
public void listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            System.out.println(fileEntry.getName());
        }
    }
}

final File folder = new File("/home/you/Desktop");
listFilesForFolder(folder);
```

**Files.walk API is available from Java 8.**

```java
try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) {
    paths
        .filter(Files::isRegularFile)
        .forEach(System.out::println);
}
```

[к оглавлению](#IO)

## Как проверить что файловая сущность является файлом или папкой.

```java
File file = new File("/Users/pankaj/source.txt");
File dir = new File("/Users/pankaj");
File notExists = new File("/Users/pankaj/notafile");
        
System.out.println("/Users/pankaj/source.txt is file?"+file.isFile());
System.out.println("/Users/pankaj/source.txt is directory?"+file.isDirectory());
        
System.out.println("/Users/pankaj is file?"+dir.isFile());
System.out.println("/Users/pankaj is directory?"+dir.isDirectory());
        
System.out.println("/Users/pankaj/notafile is file?"+notExists.isFile());
System.out.println("/Users/pankaj/notafile is directory?"+notExists.isDirectory());
```

С использованием path

```java
Path file = new File(path).toPath();

boolean exists =      Files.exists(file);        // Check if the file exists
boolean isDirectory = Files.isDirectory(file);   // Check if it's a directory
boolean isFile =      Files.isRegularFile(file); // Check if it's a regular file
```

[к оглавлению](#IO)

## Как удалить файл

**Using java.io.File.delete() function:**
```java
File file = new File("/Users/pankaj/file.txt");
if(file.delete()){
    System.out.println("/Users/pankaj/file.txt File deleted");
}else System.out.println("File /Users/pankaj/file.txt doesn't exist");
```
**Using java.nio.file.files.deleteifexists(Path p)**
```java
Files.deleteIfExists(Paths.get("C:\\Users\\Mayank\\Desktop\\445.txt")); 
```

[к оглавлению](#IO)

## Как переместить файл

Java.io.File does not contains any ready make move file method, but you can workaround with the following two alternatives :

+ File.renameTo() (может не сработать на разных файловых системах. Надо проверять результат)
+ Copy to new file and delete the original file.

Для Java 7:

+ Files.move(Paths.get("/foo.txt"), Paths.get("bar.txt"), StandardCopyOption.REPLACE_EXISTING);

[к оглавлению](#IO)

## Как управлять атрибутами файла

Базовые атрибуты (доступны во всех ОС):

+ File type
+ File size
+ Created time
+ Owner of the file
+ Last time modified
+ Last time accessed
+ Hidden
+ System file
+ Regular file
+ isDirectory

FileAttributeView - базовый интерфейс с подинтерфейсами

+ BasicFileAttributeView
+ DosFileAttributeView
+ PosixFileAttributeView
+ UserDefinedFileAttributeView
+ AclFileAttributeView
+ FileOwnerAttributeView

```java
Path path = FileSystems.getDefault().getPath("c:/test", "somefile.txt");
BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
basicView.readAttributes().lastAccessTime().toMillis();;  // will return the last time the file was read.
basicView.readAttributes().lastModifiedTime().toMillis();  // will return the last time the file was changed.
basicView.readAttributes().creationTime().toMillis();  // will return the creation time.
```

```java
DosFileAttributeView dosView = Files.getFileAttributeView(path,DosFileAttributeView.class);
dosView.setHidden(true);
dosView.setReadOnly(true);
dosView.setSystem(true);
dosView.setArchive(true);
```

[к оглавлению](#IO)

## Как создать файл

Три способа:
+ File file = new File(absoluteFilePath);
    file.createNewFile();
    
+ FileOutputStream fos = new FileOutputStream("name.txt");
  fos.write(fileData.getBytes());
  fos.flush();
  fos.close();
  
+ String fileData = "Pankaj Kumar";
  Files.write(Paths.get("name.txt"), fileData.getBytes());

[к оглавлению](#IO)

## Как создать директорию

+ new File("/path/directory").mkdirs();

+ Files.createDirectories(Paths.get("/path/to/directory"));

[к оглавлению](#IO)

## Как записать в файл

+ **BufferedWritter**
```java
    BufferedWriter writer = new BufferedWriter(new FileWriter("c:/temp/samplefile1.txt"));
    writer.write(fileContent);
    writer.close();
```
        
+ **FileWriter/PrintWriter**

```java
    FileWriter fileWriter = new FileWriter("c:/temp/samplefile2.txt");
    fileWriter.write(fileContent);
    fileWriter.close();
```

+ **FileOutputStream**

```java
    FileOutputStream outputStream = new FileOutputStream("c:/temp/samplefile4.txt");
    byte[] strToBytes = fileContent.getBytes();
    outputStream.write(strToBytes);
     
    outputStream.close();
```

+ **DataOutputStream**

```java
    FileOutputStream outputStream = new FileOutputStream("c:/temp/samplefile5.txt");
    DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));
    dataOutStream.writeUTF(fileContent);
     
    dataOutStream.close();
```

+ **FileChannel**

```java
    RandomAccessFile stream = new RandomAccessFile("c:/temp/samplefile6.txt", "rw");
    FileChannel channel = stream.getChannel();
    byte[] strBytes = fileContent.getBytes();
    ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
    buffer.put(strBytes);
    buffer.flip();
    channel.write(buffer);
    stream.close();
    channel.close();
```

+ **Java 7 Path**

```java
    Path path = Paths.get("c:/temp/samplefile7.txt");     
    Files.write(path, fileContent.getBytes());
```

**Summary**
+ If we try to write to a file that doesn’t exist, the file will be created first and no exception will be thrown (except using Path method).
+ Always close the output stream after writing the file content to release all resources. It will also help in not corrupting the file.
+ Use PrintWriter is used to write formatted text.
+ Use FileOutputStream to write binary data.
+ Use DataOutputStream to write primitive data types.
+ Use FileChannel to write larger files.

[к оглавлению](#IO)

## Как прочитать данные из файла

+ **BufferedReader**
```java
    BufferedReader br = new BufferedReader(new FileReader(file)); 
         
    String st; 
    while ((st = br.readLine()) != null) 
        System.out.println(st); 
    }
```
    
+ **FileReader**
```java
    FileReader fr = 
          new FileReader("C:\\Users\\pankaj\\Desktop\\test.txt"); 
      
    int i; 
    while ((i=fr.read()) != -1) 
    System.out.print((char) i);      
```

+ **Scanner**
```java
    Scanner sc = new Scanner(file); 
      
    // we just need to use \\Z as delimiter 
    sc.useDelimiter("\\Z"); 
      
    System.out.println(sc.next());
```

+ **Reading the whole file in a List**
```java
    data = new String(Files.readAllBytes(Paths.get(fileName)));
```

[к оглавлению](#IO)