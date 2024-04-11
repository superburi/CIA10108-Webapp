package RentalMyTrack.trackdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import RentalMyTrack.trackvo.Track;


public class TrackDaoImpl implements TrackDao{
	
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/mytest?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "Yuhan1207";
    
    private static final String GET_ONE = "SELECT rNo, memNo, rTrackTime, expRentalDate FROM rentalmytrack WHERE rNo = ? AND memNo = ?";
    private static final String GET_ALL = "SELECT rNo, memNo, rTrackTime, expRentalDate FROM rentalmytrack";
    private static final String INSERT = "INSERT INTO rentalmytrack (rNo, memNo, rTrackTime, expRentalDate) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE rentalmytrack SET expRentalDate = ? WHERE rNo = ? AND memNo = ?";
    private static final String DELETE = "DELETE FROM rentalmytrack where rNo = ? AND memNo = ?";
    
    /* 以下方法按照 增、刪、改、查 排列 */
    
	@Override
	public void insert(Track rmt) {
		
        Connection con = null;
        PreparedStatement pstmt = null;
		
		try {
			
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, rmt.getrNo());
			pstmt.setInt(2, rmt.getmemNo());
			
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(3, timestamp);
			pstmt.setDate(4, rmt.getexpRentalDate());
			
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
	public void delete(Integer rNo, Integer memNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, rNo);
			pstmt.setInt(2, memNo);
			
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
	public void update(Track rmt) {
		
        Connection con = null;
        PreparedStatement pstmt = null;
		
		try {
			
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1, rmt.getexpRentalDate());
			pstmt.setInt(2, rmt.getrNo());
			pstmt.setInt(3, rmt.getmemNo());
			
			
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
	public Track findByPK(Integer rNo, Integer memNo) {
		
        Track rmt = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE);
            
            pstmt.setInt(1, rNo);
            pstmt.setInt(2, memNo);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	
            	rmt = new Track();
				rmt.setrNo(rs.getInt("rNo"));
				rmt.setmemNo(rs.getInt("memNo"));
				rmt.setrTrackTime(rs.getTimestamp("rTrackTime"));
				rmt.setexpRentalDate(rs.getDate("expRentalDate"));
				
            	
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
        return rmt;
		
	} // findByPK 結束


	@Override
	public List<Track> getAll() {
		
        List<Track> list = new ArrayList<Track>();
        Track rmt = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	
            	rmt = new Track();
				rmt.setrNo(rs.getInt("rNo"));
				rmt.setmemNo(rs.getInt("memNo"));
				rmt.setrTrackTime(rs.getTimestamp("rTrackTime"));
				rmt.setexpRentalDate(rs.getDate("expRentalDate"));
				list.add(rmt);
            	
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
