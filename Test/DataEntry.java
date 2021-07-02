
import java.sql.*;
import java.util.Scanner;

public class DataEntry {
    public static void main(String[] args) {

        Statement st;
        PreparedStatement pst;
        ResultSet rs;
        Scanner in = new Scanner(System.in);
        int ch;
        int chk=0;
        int catid,prodid,price,qtty;
        String name,desc;
        boolean stat;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Shobhit@123");
            System.out.println("Successful!");


            if (con != null)
            {
                do {
                    System.out.println("1. View Records");
                    System.out.println("2. Insert Record");
                    System.out.println("3. Modify Record");
                    System.out.println("4. Search Record");
                    System.out.println("5. Delete Record");
                    System.out.println("6. Exit");

                   System.out.println("Enter your choice:");
                   ch=in.nextInt();


                    switch (ch)
                    {
                        case 1:
                            st = con.createStatement();
                            rs = st.executeQuery("select * from product");

                            while (rs.next())
                            {
                                System.out.println(rs.getInt("category_id")+"\t"+rs.getInt("product_id")+"\t"+rs.getString("prod_name")+"\t"+rs.getString("prod_manu")+"\t"+rs.getBoolean("Status")+"\t"+rs.getInt("Price"));
                            }
                            st.close();
                            rs.close();
                            break;
                        case 2:
                            System.out.println("Please enter Category Id:");
                            catid=in.nextInt();
                            System.out.println("Please enter Product Id:");
                            prodid=in.nextInt();
                            System.out.println("Please enter Name:");
                            name=in.next();
                            System.out.println("Please enter Manufacturer:");
                            desc=in.next();
                            desc += in.nextLine();
                            //System.out.println("Please enter Status:");
                            stat=true;
                            System.out.println("Please enter Price:");
                            price=in.nextInt();
                            System.out.println("Please enter Quantity:");
                            qtty=in.nextInt();
                            pst = con.prepareStatement("insert into product(category_id,product_id,prod_name,prod_manu,Status,Price,Quantity) values(?,?,?,?,?,?,?)");
                            pst.setInt(1,catid);
                            pst.setInt(2,prodid);
                            pst.setString(3,name);
                            pst.setString(4, desc);
                            pst.setBoolean(5, stat);
                            pst.setInt(6,price);
                            pst.setInt(7,qtty);
                            
                            pst.executeUpdate();
                            pst.close();
                            break;
                        case 3:
                            System.out.println("Please enter Laptop Id:");
                            //id=in.nextInt();
                            System.out.println("Please enter Laptop M Year that you wants to Modify?");
                            //year=in.nextInt();

                            pst = con.prepareStatement("update records set laptopmyear=? where laptopid=?");
                            //pst.setInt(1,year);
                            //pst.setInt(2,id);
                            pst.executeUpdate();
                            pst.close();
                            break;
                        case 4:
                            System.out.println("Please enter Product Id to search?:");
                            prodid=in.nextInt();
                            st = con.createStatement();
                            rs = st.executeQuery("select * from product where product_id="+prodid);

                            while (rs.next())
                            {
                                System.out.println(rs.getInt("category_id")+"\t"+rs.getInt("product_id")+"\t"+rs.getString("prod_name")+"\t"+rs.getString("prod_manu")+"\t"+rs.getBoolean("Status")+"\t"+rs.getInt("Price"));
                            }
                            st.close();
                            rs.close();
                            break;
                        case 5:
                            System.out.println("Please enter Product Id to Delete?:");
                            prodid=in.nextInt();
                            pst = con.prepareStatement("delete from product where product_id=?");
                            pst.setInt(1,prodid);
                            pst.executeUpdate();
                            pst.close();
                            break;
                        case 6:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid Choice:");

                    }


                    System.out.println("Do you wants to continue(1/0)?");
                    chk=in.nextInt();
                }

                while (chk==1);

            }


        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
