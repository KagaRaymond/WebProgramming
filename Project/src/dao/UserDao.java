package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */

	public User searchByLoginId(String loginId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            ResultSet rs = pStmt.executeQuery();

            // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
           if (!rs.next()) {
               return null;
           }

            String loginIdData = rs.getString("login_id");

            if(loginIdData == null) {
                return null;

            }else {
            	return new User(loginIdData);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

	}

    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);

            //ハッシュを生成したい元の文字列
            String source = password;
            //ハッシュ生成前にバイト配列に置き換える際のCharset
            Charset charset = StandardCharsets.UTF_8;
            //ハッシュアルゴリズム
            String algorithm = "MD5";

            //ハッシュ生成処理
            byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
            String result = DatatypeConverter.printHexBinary(bytes);
            pStmt.setString(1, loginId);
            pStmt.setString(2, result);

            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    /**
     * 条件検索にて一覧取得
     * @return
     */

    public List<User> searching(String loginIdA, String nameB, String birthDateC, String birthDateD) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE login_id != 'admin'";

            //ログインID（完全一致）
            if(!loginIdA.isEmpty()) {
            	sql = sql + " AND login_id='" + loginIdA + "'";
            }

            //ユーザ名（部分一致）
            if(!nameB.isEmpty()) {
            	sql = sql + "AND name LIKE '%" + nameB + "%'";
            }

            //生年月日（開始日と終了日の範囲内の日付に該当するもの）
           if(!birthDateC.isEmpty() || !birthDateD.isEmpty()) {
        	   if(birthDateD.isEmpty() && !birthDateC.isEmpty()) {
        		   sql = sql + "AND birth_date >='" + birthDateC + "'";
        	   }
        	   if(birthDateC.isEmpty() && !birthDateD.isEmpty()) {
        		   sql = sql + "AND birth_date <='" + birthDateD + "'";

        	   }if(!birthDateC.isEmpty() && !birthDateD.isEmpty()) {
        		   sql = sql + "AND birth_date >='" + birthDateC + "' AND birth_date <='" + birthDateD + "'";
        	   }

           }

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {


                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }



    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE login_id != 'admin'";


             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {


                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    /** 更新するメソッド **/

    public void userUpdate(String password, String name, String birth_date, String loginId) {
    	Connection conn = null;
    	try {
    		//データベースへ接続
    		conn = DBManager.getConnection();

    		//UPDATE文を準備
    		String sql = "UPDATE user SET password=?, name=?, birth_date=?, update_date=now() WHERE login_id=?;";

    		//UPDATEを実行
    		PreparedStatement stmt = conn.prepareStatement(sql);

    		//ハッシュを生成したい元の文字列
    		String source = password;
    		//ハッシュ生成前にバイト配列に置き換える際のCharset
    		Charset charset = StandardCharsets.UTF_8;
    		//ハッシュアルゴリズム
    		String algorithm = "MD5";

    		//ハッシュ生成処理
    		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
    		String result = DatatypeConverter.printHexBinary(bytes);


    		stmt.setString(1, result);
    		stmt.setString(2, name);
    		stmt.setString(3, birth_date);
    		stmt.setString(4, loginId);

    		stmt.executeUpdate();
    		stmt.close();

    	}catch(SQLException | NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}finally {
   		 // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    	}

    }
    /**
     * idに紐づくユーザ情報を取得する
     * @return
     */
    public void userDelete(String id) {
    	Connection conn = null;
    	try {
    		//データベースへ接続
    		conn = DBManager.getConnection();
    		//DELETE文を準備
    		String sql = "DELETE FROM user WHERE login_id =?;";

    		//DELETEを実行
    		PreparedStatement stmt = conn.prepareStatement(sql);

    		stmt.setString(1,id);

    		stmt.executeUpdate();
    		stmt.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		//データベース切断
    		if(conn != null) {
    			try {
    				conn.close();
    			}catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }

    public void userInsert(String loginId, String password, String name, String birth_date) {
    	Connection conn = null;
    	try {
    		//データベースへ接続
    		conn = DBManager.getConnection();
    		//INSERT文を準備
    		String sql = "INSERT INTO user(login_id, password, name, birth_date, create_date, update_date)VALUES(?,?,?,?,now(),now());";
    		//INSERTを実行
    		PreparedStatement stmt = conn.prepareStatement(sql);

    		//ハッシュを生成したい元の文字列
            String source = password;
            //ハッシュ生成前にバイト配列に置き換える際のCharset
            Charset charset = StandardCharsets.UTF_8;
            //ハッシュアルゴリズム
            String algorithm = "MD5";

            //ハッシュ生成処理
            byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
            String result = DatatypeConverter.printHexBinary(bytes);

    		stmt.setString(1,loginId);
    		stmt.setString(2,result);
    		stmt.setString(3,name);
    		stmt.setString(4,birth_date);



    		stmt.executeUpdate();
    		stmt.close();
    	}catch(SQLException | NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}finally {
    		 // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    	}
    }
    /** パスワード以外を更新するメソッド **/

    public void updateInsert(String name, String birth_date, String loginId) {
    	Connection conn = null;
    	try {
    		//データベースへ接続
    		conn = DBManager.getConnection();

    		//UPDATE文を準備
    		String sql = "UPDATE user SET name=?, birth_date=?, update_date=now() WHERE login_id=?;";

    		//UPDATEを実行
    		PreparedStatement stmt = conn.prepareStatement(sql);

    		stmt.setString(1, name);
    		stmt.setString(2, birth_date);
    		stmt.setString(3, loginId);

    		stmt.executeUpdate();
    		stmt.close();

    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
   		 // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    	}

    }


    public static void main(String[]args) {
    	Connection conn = null;
    	try {
    		//データベースへ接続
    		conn = DBManager.getConnection();

    		//SELECT文を準備
    		String sql = "SELECT login_id, name, birth_date, create_date FROM user";

    		//SELECTを実行し、結果表（ResultSet）を取得
    		Statement stmt = conn. createStatement();
    		ResultSet rs = stmt.executeQuery(sql);

    		while(rs.next()) {
    			String login_id = rs.getString("login_id");
    			String name = rs.getString("name");
    			String birth_date = rs.getString("birth_date");
    			String create_date = rs.getString("create_date");
    		}
    		stmt.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		//データベース切断
    		if(conn !=null) {
    			try {
    				conn.close();
    			}catch(SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    /**
     * idに紐づくユーザ情報を取得する
     * @return
     */
    public User findUserById(String id) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id=?";

             // SELECTを実行し、結果表を取得
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            if (rs.next()) {
                int Id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(Id, loginId, name, birthDate, password, createDate, updateDate);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}
