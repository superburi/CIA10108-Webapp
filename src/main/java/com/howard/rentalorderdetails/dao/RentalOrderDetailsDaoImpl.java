package com.howard.rentalorderdetails.dao;

import com.howard.rentalorderdetails.vo.RentalOrderDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RentalOrderDetailsDaoImpl implements RentalOrderDetailsDao {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/mytest?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "Yuhan1207";

    private static final String GET_ONE = "SELECT rOrdNo, rNo, rPrice, rDesPrice FROM rentalorderdetails WHERE rOrdNo = ? AND rNo = ?";
    private static final String GET_ALL = "SELECT rOrdNo, rNo, rPrice, rDesPrice FROM rentalorderdetails";
    private static final String INSERT = "INSERT INTO rentalorderdetails (rOrdNo, rNo, rPrice, rDesPrice) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE rentalorderdetails SET rPrice = ?, rDesPrice = ? WHERE rOrdNo = ? AND rNo = ?";
    private static final String DELETE = "DELETE FROM rentalorderdetails where rOrdNo = ? AND rNo = ?";

    /* 以下方法按照 增、刪、改、查 排列 */

	@Override
	public void insert(RentalOrderDetails rentalOrderDetails) {

        Connection con = null;
        PreparedStatement pstmt = null;

		try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, rentalOrderDetails.getrOrdNo());
			pstmt.setInt(2, rentalOrderDetails.getrNo());
            pstmt.setBigDecimal(3, rentalOrderDetails.getrPrice());
            pstmt.setBigDecimal(4, rentalOrderDetails.getrDesPrice());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

	} // insert 結束


	@Override
	public void delete(Integer rOrdNo, Integer rNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rOrdNo);
			pstmt.setInt(2, rNo);

			pstmt.executeUpdate();

			// 若 driver 出問題
		} catch(ClassNotFoundException e) {

            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // 若 SQL 出問題
		} catch(SQLException se) {

            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}

	}// delete 結束


	@Override
	public void update(RentalOrderDetails rentalOrderDetails) {

        Connection con = null;
        PreparedStatement pstmt = null;

		try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setBigDecimal(1, rentalOrderDetails.getrPrice());
            pstmt.setBigDecimal(2, rentalOrderDetails.getrDesPrice());
			pstmt.setInt(3, rentalOrderDetails.getrOrdNo());
			pstmt.setInt(4, rentalOrderDetails.getrNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

	} // update 結束


	@Override
	public RentalOrderDetails findByPK(Integer rOrdNo, Integer rNo) {

        RentalOrderDetails rentalOrderDetails = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE);

            pstmt.setInt(1, rOrdNo);
            pstmt.setInt(2, rNo);

            rs = pstmt.executeQuery();

            while(rs.next()) {

            	rentalOrderDetails = new RentalOrderDetails();
                rentalOrderDetails.setrOrdNo(rs.getInt("rOrdNo"));
                rentalOrderDetails.setrNo(rs.getInt("rNo"));
                rentalOrderDetails.setrPrice(rs.getBigDecimal("rPrice"));
                rentalOrderDetails.setrDesPrice(rs.getBigDecimal("rDesPrice"));

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return rentalOrderDetails;

	} // findByPK 結束


	@Override
	public List<RentalOrderDetails> getAll() {

        List<RentalOrderDetails> list = new ArrayList<RentalOrderDetails>();
        RentalOrderDetails rentalOrderDetails = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();

            while(rs.next()) {

                rentalOrderDetails = new RentalOrderDetails();
                rentalOrderDetails.setrOrdNo(rs.getInt("rOrdNo"));
                rentalOrderDetails.setrNo(rs.getInt("rNo"));
                rentalOrderDetails.setrPrice(rs.getBigDecimal("rPrice"));
                rentalOrderDetails.setrDesPrice(rs.getBigDecimal("rDesPrice"));
				list.add(rentalOrderDetails);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return list;

	} // getAll 結束


}
