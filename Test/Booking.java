import java.sql.*;
import java.util.Scanner;
public class Booking {
    public static void main(String[] args) {
        int ch;
        Statement st;
        PreparedStatement pst;
        ResultSet rs;
        Scanner sc = new Scanner(System.in);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Shobhit@123");
            System.out.println("Successful!");
            System.out.println("What type of Bicycle do you want?\n1. Gearless\n2. Geared\n3. MTB");
            ch=sc.nextInt();
            st = con.createStatement();
            rs = st.executeQuery("select * from product where category_id="+ch);
            while (rs.next())
            {
                System.out.println("\t\t"+rs.getInt("product_id")+"\t"+rs.getString("prod_name")+"\t\t"+rs.getString("prod_manu")+"\t"+rs.getInt("Price"));
            }
            st.close();
            rs.close();
            System.out.println("Select Your Bike(Enter ID)");
            ch=sc.nextInt();
            pst = con.prepareStatement("update product set Status=? where product_id=?");
            pst.setBoolean(1, false);
            pst.setInt(2,ch);
            pst.executeUpdate();
            pst.close();
            System.out.println("Your Order has been received");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
