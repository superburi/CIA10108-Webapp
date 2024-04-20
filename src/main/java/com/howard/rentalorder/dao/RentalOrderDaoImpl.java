package com.howard.rentalorder.dao;

import com.howard.rentalorder.vo.RentalOrderVo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RentalOrderDaoImpl implements RentalOrderDao {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/mytest?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "Yuhan1207";

    private static final String INSERT = "INSERT INTO rentalorder (memNo, rByrName, rByrPhone, rByrEmail, " +
            "rRcvName, rRcvPhone, rTakeMethod, rAddr, rPayMethod, rAllPrice, rAllDepPrice, rOrdTime," +
            " rDate, rBackDate, rRealBackDate, rPayStat, rOrdStat, rtnStat, rtnRemark, rtnCompensation) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM rentalorder where rOrdNo = ?";
    private static final String UPDATE = "UPDATE rentalorder SET memNo = ?, rByrName = ?, rByrPhone = ?, " +
            "rByrEmail = ?, rRcvName = ?, rRcvPhone = ?, rTakeMethod = ?, rAddr = ?, rPayMethod = ?, " +
            "rAllPrice = ?, rAllDepPrice = ?, rOrdTime = ?, rDate = ?, rBackDate = ?, rRealBackDate = ?, " +
            "rPayStat = ?, rOrdStat = ?, rtnStat = ?, rtnRemark = ?, rtnCompensation = ? " +
            "WHERE rOrdNo = ?";
    private static final String GET_ON_ANY = "SELECT rOrdNo, memNo, rByrName, rByrPhone, rByrEmail, rRcvName, " +
            "rRcvPhone, rTakeMethod, rAddr, rPayMethod, rAllPrice, rAllDepPrice, rOrdTime, rDate, rBackDate, " +
            "rRealBackDate, rPayStat, rOrdStat, rtnStat, rtnRemark, rtnCompensation " +
            "FROM rentalorder " +
            "WHERE 1=1";
    private static final String COUNT_ON_ANY = "SELECT COUNT(*) FROM rentalorder WHERE 1=1";


/* 以下方法按照 增、刪、改、查 排列 */

    @Override
    public void insert(RentalOrderVo rentalOrderVo) {

        Connection con = null;
        PreparedStatement pstmt = null;

		try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, rentalOrderVo.getMemNo());
			pstmt.setString(2, rentalOrderVo.getrByrName());
            pstmt.setString(3,rentalOrderVo.getrByrPhone() );
            pstmt.setString(4, rentalOrderVo.getrByrEmail());
            pstmt.setString(5, rentalOrderVo.getrRcvName());
            pstmt.setString(6, rentalOrderVo.getrRcvPhone());
            pstmt.setByte(7, rentalOrderVo.getrTakeMethod());
            pstmt.setString(8, rentalOrderVo.getrAddr());
            pstmt.setByte(9, rentalOrderVo.getrPayMethod());
            pstmt.setBigDecimal(10, rentalOrderVo.getrAllPrice());
            pstmt.setBigDecimal(11, rentalOrderVo.getrAllDepPrice());
            pstmt.setTimestamp(12, rentalOrderVo.getrOrdTime());
            pstmt.setTimestamp(13, rentalOrderVo.getrDate());
            pstmt.setTimestamp(14, rentalOrderVo.getrBackDate());
            pstmt.setTimestamp(15, rentalOrderVo.getrRealBackDate());
            pstmt.setByte(16, rentalOrderVo.getrPayStat());
            pstmt.setByte(17, rentalOrderVo.getrOrdStat());
            pstmt.setByte(18, rentalOrderVo.getRtnStat());
            pstmt.setString(19, rentalOrderVo.getRtnRemark());
            pstmt.setBigDecimal(20, rentalOrderVo.getRtnCompensation());

			pstmt.executeUpdate();

            // 新增訂單後，取得自動生成的 OrdNo
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                int rOrdNo = rs.getInt(1);
//                return rOrdNo;
//            } else {
//                return (-1);
//            }

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
    public void delete(Integer rOrdNo) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, rOrdNo);
            pstmt.executeUpdate();
            // 若 driver 出問題
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // 若 SQL 出問題
		} catch (SQLException se) {
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

    } // delete 結束

    @Override
    public void update(RentalOrderVo rentalOrderVo) {

        Connection con = null;
        PreparedStatement pstmt = null;

		try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, rentalOrderVo.getMemNo());
            pstmt.setString(2, rentalOrderVo.getrByrName());
            pstmt.setString(3,rentalOrderVo.getrByrPhone() );
            pstmt.setString(4, rentalOrderVo.getrByrEmail());
            pstmt.setString(5, rentalOrderVo.getrRcvName());
            pstmt.setString(6, rentalOrderVo.getrRcvPhone());
            pstmt.setByte(7, rentalOrderVo.getrTakeMethod());
            pstmt.setString(8, rentalOrderVo.getrAddr());
            pstmt.setByte(9, rentalOrderVo.getrPayMethod());
            pstmt.setBigDecimal(10, rentalOrderVo.getrAllPrice());
            pstmt.setBigDecimal(11, rentalOrderVo.getrAllDepPrice());
            pstmt.setTimestamp(12, rentalOrderVo.getrOrdTime());
            pstmt.setTimestamp(13, rentalOrderVo.getrDate());
            pstmt.setTimestamp(14, rentalOrderVo.getrBackDate());
            pstmt.setTimestamp(15, rentalOrderVo.getrRealBackDate());
            pstmt.setByte(16, rentalOrderVo.getrPayStat());
            pstmt.setByte(17, rentalOrderVo.getrOrdStat());
            pstmt.setByte(18, rentalOrderVo.getRtnStat());
            pstmt.setString(19, rentalOrderVo.getRtnRemark());
            pstmt.setBigDecimal(20, rentalOrderVo.getRtnCompensation());
            pstmt.setInt(21, rentalOrderVo.getrOrdNo());

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
    public RentalOrderVo findByPK(Integer rOrdNo) {

        RentalOrderVo rentalOrderVo = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ON_ANY + " AND rOrdNo = ?");

            pstmt.setInt(1, rOrdNo);

            rs = pstmt.executeQuery();

            while(rs.next()) {

            	rentalOrderVo = new RentalOrderVo();
                rentalOrderVo.setrOrdNo(rs.getInt("rOrdNo"));
                rentalOrderVo.setMemNo(rs.getInt("memNo"));
                rentalOrderVo.setrByrName(rs.getString("rByrName"));
                rentalOrderVo.setrByrPhone(rs.getString("rByrPhone"));
                rentalOrderVo.setrByrEmail(rs.getString("rByrEmail"));
                rentalOrderVo.setrRcvName(rs.getString("rRcvName"));
                rentalOrderVo.setrRcvPhone(rs.getString("rRcvPhone"));
                rentalOrderVo.setrTakeMethod(rs.getByte("rTakeMethod"));
                rentalOrderVo.setrAddr(rs.getString("rAddr"));
                rentalOrderVo.setrPayMethod(rs.getByte("rPayMethod"));
                rentalOrderVo.setrAllPrice(rs.getBigDecimal("rAllPrice"));
                rentalOrderVo.setrAllDepPrice(rs.getBigDecimal("rAllDepPrice"));
                rentalOrderVo.setrOrdTime(rs.getTimestamp("rOrdTime"));
                rentalOrderVo.setrDate(rs.getTimestamp("rDate"));
                rentalOrderVo.setrBackDate(rs.getTimestamp("rBackDate"));
                rentalOrderVo.setrRealBackDate(rs.getTimestamp("rRealBackDate"));
                rentalOrderVo.setrPayStat(rs.getByte("rPayStat"));
                rentalOrderVo.setrOrdStat(rs.getByte("rOrdStat"));
                rentalOrderVo.setRtnStat(rs.getByte("rtnStat"));
                rentalOrderVo.setRtnRemark(rs.getString("rtnRemark"));
                rentalOrderVo.setRtnCompensation(rs.getBigDecimal("rtnCompensation"));

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

        return rentalOrderVo;

    } // findByPK 結束

    @Override
    public List<RentalOrderVo> findByName(String rByrName) {

        List<RentalOrderVo> rentalOrderVoList = new ArrayList<>();
        RentalOrderVo rentalOrderVo = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ON_ANY + " AND rByrName LIKE ?");

            pstmt.setString(1, "%" + rByrName + "%");

            rs = pstmt.executeQuery();

            while(rs.next()) {

                rentalOrderVo = new RentalOrderVo();
                rentalOrderVo.setrOrdNo(rs.getInt("rOrdNo"));
                rentalOrderVo.setMemNo(rs.getInt("memNo"));
                rentalOrderVo.setrByrName(rs.getString("rByrName"));
                rentalOrderVo.setrByrPhone(rs.getString("rByrPhone"));
                rentalOrderVo.setrByrEmail(rs.getString("rByrEmail"));
                rentalOrderVo.setrRcvName(rs.getString("rRcvName"));
                rentalOrderVo.setrRcvPhone(rs.getString("rRcvPhone"));
                rentalOrderVo.setrTakeMethod(rs.getByte("rTakeMethod"));
                rentalOrderVo.setrAddr(rs.getString("rAddr"));
                rentalOrderVo.setrPayMethod(rs.getByte("rPayMethod"));
                rentalOrderVo.setrAllPrice(rs.getBigDecimal("rAllPrice"));
                rentalOrderVo.setrAllDepPrice(rs.getBigDecimal("rAllDepPrice"));
                rentalOrderVo.setrOrdTime(rs.getTimestamp("rOrdTime"));
                rentalOrderVo.setrDate(rs.getTimestamp("rDate"));
                rentalOrderVo.setrBackDate(rs.getTimestamp("rBackDate"));
                rentalOrderVo.setrRealBackDate(rs.getTimestamp("rRealBackDate"));
                rentalOrderVo.setrPayStat(rs.getByte("rPayStat"));
                rentalOrderVo.setrOrdStat(rs.getByte("rOrdStat"));
                rentalOrderVo.setRtnStat(rs.getByte("rtnStat"));
                rentalOrderVo.setRtnRemark(rs.getString("rtnRemark"));
                rentalOrderVo.setRtnCompensation(rs.getBigDecimal("rtnCompensation"));

                rentalOrderVoList.add(rentalOrderVo);

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

        return rentalOrderVoList;

    } // fingByName 結束

    @Override
    public List<RentalOrderVo> findByMemNo(Integer memNo) {

        List<RentalOrderVo> rentalOrderVoList = new ArrayList<>();
        RentalOrderVo rentalOrderVo = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ON_ANY + " AND memNo = ?");

            pstmt.setInt(1, memNo);

            rs = pstmt.executeQuery();

            while(rs.next()) {

                rentalOrderVo = new RentalOrderVo();
                rentalOrderVo.setrOrdNo(rs.getInt("rOrdNo"));
                rentalOrderVo.setMemNo(rs.getInt("memNo"));
                rentalOrderVo.setrByrName(rs.getString("rByrName"));
                rentalOrderVo.setrByrPhone(rs.getString("rByrPhone"));
                rentalOrderVo.setrByrEmail(rs.getString("rByrEmail"));
                rentalOrderVo.setrRcvName(rs.getString("rRcvName"));
                rentalOrderVo.setrRcvPhone(rs.getString("rRcvPhone"));
                rentalOrderVo.setrTakeMethod(rs.getByte("rTakeMethod"));
                rentalOrderVo.setrAddr(rs.getString("rAddr"));
                rentalOrderVo.setrPayMethod(rs.getByte("rPayMethod"));
                rentalOrderVo.setrAllPrice(rs.getBigDecimal("rAllPrice"));
                rentalOrderVo.setrAllDepPrice(rs.getBigDecimal("rAllDepPrice"));
                rentalOrderVo.setrOrdTime(rs.getTimestamp("rOrdTime"));
                rentalOrderVo.setrDate(rs.getTimestamp("rDate"));
                rentalOrderVo.setrBackDate(rs.getTimestamp("rBackDate"));
                rentalOrderVo.setrRealBackDate(rs.getTimestamp("rRealBackDate"));
                rentalOrderVo.setrPayStat(rs.getByte("rPayStat"));
                rentalOrderVo.setrOrdStat(rs.getByte("rOrdStat"));
                rentalOrderVo.setRtnStat(rs.getByte("rtnStat"));
                rentalOrderVo.setRtnRemark(rs.getString("rtnRemark"));
                rentalOrderVo.setRtnCompensation(rs.getBigDecimal("rtnCompensation"));

                rentalOrderVoList.add(rentalOrderVo);

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

        return rentalOrderVoList;

    }

    @Override
    public List<RentalOrderVo> getAll() {

        List<RentalOrderVo> rentalOrderVoList = new ArrayList<RentalOrderVo>();
        RentalOrderVo rentalOrderVo = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ON_ANY);
            rs = pstmt.executeQuery();

            while(rs.next()) {

                rentalOrderVo = new RentalOrderVo();
                rentalOrderVo.setrOrdNo(rs.getInt("rOrdNo"));
                rentalOrderVo.setMemNo(rs.getInt("memNo"));
                rentalOrderVo.setrByrName(rs.getString("rByrName"));
                rentalOrderVo.setrByrPhone(rs.getString("rByrPhone"));
                rentalOrderVo.setrByrEmail(rs.getString("rByrEmail"));
                rentalOrderVo.setrRcvName(rs.getString("rRcvName"));
                rentalOrderVo.setrRcvPhone(rs.getString("rRcvPhone"));
                rentalOrderVo.setrTakeMethod(rs.getByte("rTakeMethod"));
                rentalOrderVo.setrAddr(rs.getString("rAddr"));
                rentalOrderVo.setrPayMethod(rs.getByte("rPayMethod"));
                rentalOrderVo.setrAllPrice(rs.getBigDecimal("rAllPrice"));
                rentalOrderVo.setrAllDepPrice(rs.getBigDecimal("rAllDepPrice"));
                rentalOrderVo.setrOrdTime(rs.getTimestamp("rOrdTime"));
                rentalOrderVo.setrDate(rs.getTimestamp("rDate"));
                rentalOrderVo.setrBackDate(rs.getTimestamp("rBackDate"));
                rentalOrderVo.setrRealBackDate(rs.getTimestamp("rRealBackDate"));
                rentalOrderVo.setrPayStat(rs.getByte("rPayStat"));
                rentalOrderVo.setrOrdStat(rs.getByte("rOrdStat"));
                rentalOrderVo.setRtnStat(rs.getByte("rtnStat"));
                rentalOrderVo.setRtnRemark(rs.getString("rtnRemark"));
                rentalOrderVo.setRtnCompensation(rs.getBigDecimal("rtnCompensation"));

                rentalOrderVoList.add(rentalOrderVo);

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

        return rentalOrderVoList;

    } // getAll 結束

    @Override
    public int countBy(String rByrName, Integer memNo) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int count = 0;
        // 如果用會員編號查總筆數
        if (rByrName.equals("#")) {

            try {

                Class.forName(driver);
                con = DriverManager.getConnection(url, userid, passwd);
                pstmt = con.prepareStatement(COUNT_ON_ANY + " AND memNo = ?");

                pstmt.setInt(1, memNo);

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    count = rs.getInt(1); // 使用索引 1 獲取結果集的第一列數值
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

        }
        // 如果用訂購人姓名查總筆數
        if (memNo == -99999) {

            try {

                Class.forName(driver);
                con = DriverManager.getConnection(url, userid, passwd);
                pstmt = con.prepareStatement(COUNT_ON_ANY + " AND rByrName LIKE ?");

                pstmt.setString(1, "%" + rByrName + "%");

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    count = rs.getInt(1); // 使用索引 1 獲取結果集的第一列數值
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

        }
        // 如果無條件查總筆數
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(COUNT_ON_ANY);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1); // 使用索引 1 獲取第一列數值
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

        return count;
    }

}
