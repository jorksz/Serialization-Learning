package avro.start;

import avro.message.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class AvroMain {

    public static void main(String[] args) throws IOException {
        // 构造对象
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        User user2 = new User("Ben", 7, "red");
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();

        // 序列化，写入磁盘文件
        SpecificDatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File("users.avro"));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();

        //反序列化，读取内容
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<>(new File("users.avro"), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
        }
        System.out.println(user);
    }
}
