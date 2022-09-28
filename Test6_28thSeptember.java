package Test6_28thSept;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Test6_28thSeptember {
    public static void main(String[] args)
    {
        try{

            Connection connection;
            Statement statement;
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","varansi2011");
            statement=connection.createStatement();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            String query2="Create table author(id int Primary key auto_increment, name varchar(100))";
            statement.execute(query2);
            String query3="Insert into author(name)values(?)";
            preparedStatement=connection.prepareStatement(query3);
            System.out.println("Enter the number of Author entries you want to add");
            int authorEntries=Integer.parseInt(bufferedReader.readLine());
            for(int i=0;i<authorEntries;i++)
            {
                System.out.println("Enter name of the author");
                String authorName=bufferedReader.readLine();
                preparedStatement.setString(1,authorName);
                preparedStatement.executeUpdate();

            }
            String query="Create table book(id int Primary key auto_increment ,name varchar(100),price int,author_id int ,foreign key(author_id) references author(id))";
            statement.execute(query);
            String query1="Insert into book(author_id,name,price) values(?,?,?)";
            preparedStatement=connection.prepareStatement(query1);
            System.out.println("Enter the number of data you want to insert");
            int entries=Integer.parseInt(bufferedReader.readLine());
            for(int i=0;i<entries;i++)
            {
                System.out.println("enter id");
                int id=Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter name");
                String name= bufferedReader.readLine();
                System.out.println("Enter the price");
                int price=Integer.parseInt(bufferedReader.readLine());
                preparedStatement.setString(2,name);
                preparedStatement.setInt(3,price);
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            }
            String query4="Select Author.name,book.name,book.price from author,book where book.author_id=author.id";
            resultSet=statement.executeQuery(query4);
            while (resultSet.next())
            {
                System.out.println("name"+resultSet.getString(1)+
                        "Book name"+resultSet.getString(2)
                +"Book_price"+resultSet.getInt(3));
            }
            System.out.println("Enter the price you want to upgrade");
            int price=Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the book id you want change for");
            int id1=Integer.parseInt(bufferedReader.readLine());
            String query5="Update book.price="+price+"where book.author_id="+id1;
            resultSet=statement.executeQuery(query5);
            while (resultSet.next())
            {
                System.out.println("name"+resultSet.getString(1)+
                        "Book name"+resultSet.getString(2)
                        +"Book_price"+resultSet.getInt(3));
            }
            System.out.println("Enter the bool id you want to delete");
            int bookIdDelete=Integer.parseInt(bufferedReader.readLine());
             String query6="DELETE FROM book WHERE id"+bookIdDelete;
            resultSet=statement.executeQuery(query6);
            {
                System.out.println("name"+resultSet.getString(1)+
                        "Book name"+resultSet.getString(2)
                        +"Book_price"+resultSet.getInt(3));
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
