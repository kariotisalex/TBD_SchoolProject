/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;

/**
 *
 * @author alexa
 */
public class StoredProceduresAdmin {
    

    static void dropStoreProcedures(){
        dropVathmologiaStoredProcedures();
        dropMathimaStoredProcedure();
        dropMathitisStoredProcedure();
        dropKathigitisStoredProcedure();
    }
    
    static void createStoreProcedures(){
        createKathigitisStoredProcedure();
        createMathitisStoredProcedure();
        createMathimaStoredProcedure();
        createVathmologiaStoredProcedures();        
    }
    
    private static void dropVathmologiaStoredProcedures(){
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmTwoTermsWithFinalExams();");} catch (SQLException e) {System.out.println("DROP :2 " +e.getMessage());}
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmOnePlusTwoTerms();");} catch (SQLException e) {System.out.println("DROP :3 "         +e.getMessage());}
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmFinalExams();");} catch (SQLException e) {System.out.println("DROP :4 "              +e.getMessage());}
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmSecondTerm();");} catch (SQLException e) {System.out.println("DROP :5 "+e.getMessage());}
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmFirstTerm();");} catch (SQLException e) {System.out.println("DROP :6 "+e.getMessage());}
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmStudentViaID(int);");} catch (SQLException e) {System.out.println("DROP : 7 "+e.getMessage());}
    try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION selVathmLessonViaID(int);");} catch (SQLException e) {System.out.println("DROP : 8 "+e.getMessage());}
    }
    private static void dropMathitisStoredProcedure(){
    Statement psql = DBPostresqlAdmin.getStatement();
    try {psql.executeUpdate("DROP FUNCTION showtablemathitis();");} catch (SQLException e) {System.out.println("DROP :2 " +e.getMessage());}
    try {psql.executeUpdate("DROP FUNCTION showtablemathitisyearly(int);");} catch (SQLException e) {System.out.println("DROP :3 "         +e.getMessage());}

}
    private static void dropMathimaStoredProcedure(){
        try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION showtablemathima();");} 
        catch (SQLException e) {
            System.out.println("dropMathitisStoredProcedure : " + e.getMessage());
     
        }
        
        
    }
    private static void dropKathigitisStoredProcedure(){
        try {DBPostresqlAdmin.getStatement().executeUpdate("DROP FUNCTION showtablekathigitis();");} 
        catch (SQLException e) {
            System.out.println("dropMathitisStoredProcedure : " + e.getMessage());
     
        }
        
    }
        
    
    private static void createVathmologiaStoredProcedures(){
        Statement psql = DBPostresqlAdmin.getStatement();
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmStudentViaID(int)\n" +
                                "returns table(name varchar,\n" +
                                "	      surname varchar,\n" +
                                "	      nameFathers varchar,\n" +
                                "	      nameMothers varchar,\n" +
                                "	      year int) as\n" +
                                "$$\n" +
                                "SELECT onoma, eponymo, onPateras, onMiteras, etosEisagogis FROM mathitis WHERE sid = $1;\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        
        }
        
        
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmLessonViaID(int)\n" +
                                "returns table(onomamath varchar,\n" +
                                "			  onomakathigiti varchar,\n" +
                                "	  		  epithetokathigiti varchar) as\n" +
                                "$$\n" +
                                "SELECT M.onoma_mathimatos, K.onoma_kathigiti, K.epitheto_kathigiti FROM mathima M, kathigitis K WHERE K.kid=M.kid AND mid = $1;\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
        
        
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmTwoTermsWithFinalExams()\n" +
                                "returns table (\n" +
                                "					sid bigint, \n" +
                                "					onoma varchar, \n" +
                                "					eponymo varchar, \n" +
                                "					onoma_mathimatos varchar, \n" +
                                "					etos int,\n" +
                                "					vathmosProtos int,\n" +
                                "					vathmosDeuteros int,\n" +
                                "					vathmosTritos int\n" +
                                "					) as\n" +
                                "$$\n" +
                                "SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos, v2.vathmos, v3.vathmos\n" +
                                "                               FROM vathmologia V1, vathmologia v2, vathmologia v3, mathitis m1, mathima m2\n" +
                                "                               WHERE v1.sid      =  v2.sid  AND\n" +
                                "                                    v2.sid      =  v3.sid  AND\n" +
                                "                                     v1.mid      =  v2.mid  AND\n" +
                                "                                     v2.mid      =  v3.mid  AND\n" +
                                "                                    v1.etos     =  v2.etos AND\n" +
                                "                                     v2.etos     =  v3.etos AND\n" +
                                "                                     v1.eksamino =   1      AND\n" +
                                "                                     v2.eksamino =   2      AND\n" +
                                "                                     v3.eksamino =   3      AND\n" +
                                "                                     m1.sid      =  v1.sid  AND\n" +
                                "                                     m2.mid      =  v1.mid;\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
        
        
        
        
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmTwoTermsWithFinalExams()\n" +
                                "returns table (\n" +
                                "					sid bigint, \n" +
                                "					onoma varchar, \n" +
                                "					eponymo varchar, \n" +
                                "					onoma_mathimatos varchar, \n" +
                                "					etos int,\n" +
                                "					vathmosProtos int,\n" +
                                "					vathmosDeuteros int,\n" +
                                "					vathmosTritos int\n" +
                                "					) as\n" +
                                "$$\n" +
                                "SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos, v2.vathmos, v3.vathmos\n" +
                                "                               FROM vathmologia V1, vathmologia v2, vathmologia v3, mathitis m1, mathima m2\n" +
                                "                               WHERE v1.sid      =  v2.sid  AND\n" +
                                "                                    v2.sid      =  v3.sid  AND\n" +
                                "                                     v1.mid      =  v2.mid  AND\n" +
                                "                                     v2.mid      =  v3.mid  AND\n" +
                                "                                    v1.etos     =  v2.etos AND\n" +
                                "                                     v2.etos     =  v3.etos AND\n" +
                                "                                     v1.eksamino =   1      AND\n" +
                                "                                     v2.eksamino =   2      AND\n" +
                                "                                     v3.eksamino =   3      AND\n" +
                                "                                     m1.sid      =  v1.sid  AND\n" +
                                "                                     m2.mid      =  v1.mid;\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
        
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmOnePlusTwoTerms()\n" +
                                "returns table (\n" +
                                "					sid bigint, \n" +
                                "					onoma varchar, \n" +
                                "					eponymo varchar, \n" +
                                "					onoma_mathimatos varchar, \n" +
                                "					etos int,\n" +
                                "					vathmosProtos int,\n" +
                                "					vathmosDeuteros int\n" +
                                "					) as\n" +
                                "$$\n" +
                                "SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos, v2.vathmos\n" +
                                "    FROM vathmologia V1, vathmologia v2, mathitis m1, mathima m2\n" +
                                "    WHERE v1.sid=v2.sid AND\n" +
                                "          v1.mid=v2.mid AND\n" +
                                "          v1.etos=v2.etos AND\n" +
                                "          v1.eksamino = 1 AND\n" +
                                "          v2.eksamino = 2 AND\n" +
                                "          m1.sid = v1.sid AND\n" +
                                "          m2.mid = v2.mid\n" +
                                "    EXCEPT\n" +
                                "    SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos, v2.vathmos\n" +
                                "    FROM vathmologia V1, vathmologia v2,vathmologia v3, mathitis m1, mathima m2\n" +
                                "    WHERE v1.sid=v2.sid AND\n" +
                                "          v2.sid=v3.sid AND\n" +
                                "          v1.mid=v2.mid AND\n" +
                                "          v2.mid=v3.mid AND\n" +
                                "          v1.etos=v2.etos AND\n" +
                                "          v2.etos=v3.etos AND\n" +
                                "          v1.eksamino = 1 AND\n" +
                                "          v2.eksamino = 2 AND\n" +
                                "          v3.eksamino = 3 AND\n" +
                                "          m1.sid = v1.sid AND\n" +
                                "          m2.mid = v2.mid;\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
        
        
        
        
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmFinalExams()\n" +
                                "returns table (\n" +
                                "					sid bigint, \n" +
                                "					onoma varchar, \n" +
                                "					eponymo varchar, \n" +
                                "					onoma_mathimatos varchar, \n" +
                                "					etos int,\n" +
                                "					vathmosProtos text,\n" +
                                "					vathmosDeuteros text,\n" +
                                "					vathmosTritos int					\n" +
                                "					) as\n" +
                                "$$\n" +
                                "\n" +
                                "SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos,null,null, v1.vathmos\n" +
                                "FROM vathmologia V1, mathitis m1, mathima m2\n" +
                                "WHERE v1.eksamino=3 AND\n" +
                                "      m1.sid = v1.sid AND\n" +
                                "      m2.mid = v1.mid\n" +
                                "EXCEPT\n" +
                                "SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos,null,null, v3.vathmos\n" +
                                "FROM vathmologia V1, vathmologia v2,vathmologia v3, mathitis m1, mathima m2\n" +
                                "WHERE v1.sid=v2.sid AND\n" +
                                "      v2.sid=v3.sid AND\n" +
                                "      v1.mid=v2.mid AND\n" +
                                "      v2.mid=v3.mid AND\n" +
                                "      v1.etos=v2.etos AND\n" +
                                "      v2.etos=v3.etos AND\n" +
                                "      v1.eksamino = 1 AND\n" +
                                "      v2.eksamino = 2 AND\n" +
                                "      v3.eksamino = 3 AND\n" +
                                "      m1.sid = v1.sid AND\n" +
                                "      m2.mid = v2.mid\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
        
        
        
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmSecondTerm()\n" +
                                "returns table (		sid bigint, \n" +
                                "					onoma varchar, \n" +
                                "					eponymo varchar, \n" +
                                "					onoma_mathimatos varchar, \n" +
                                "					etos int,\n" +
                                "					vathmosProtos text,\n" +
                                "					vathmosDeutero int					\n" +
                                "					) as\n" +
                                "$$\n" +
                                "\n" +
                                "	SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, null, v1.vathmos\n" +
                                "	FROM vathmologia V1, mathitis m1, mathima m2\n" +
                                "	WHERE v1.eksamino=2 AND\n" +
                                "	      m1.sid = v1.sid AND\n" +
                                "	      m2.mid = v1.mid\n" +
                                "	      \n" +
                                "  	EXCEPT\n" +
                                "\n" +
                                "	SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, null, v2.vathmos\n" +
                                "	FROM vathmologia V1, vathmologia v2, mathitis m1, mathima m2\n" +
                                "	WHERE v1.sid=v2.sid AND\n" +
                                "	      v1.mid=v2.mid AND\n" +
                                "	      v1.etos=v2.etos AND\n" +
                                "	      v1.eksamino = 1 AND\n" +
                                "	      v2.eksamino = 2 AND\n" +
                                "	      m1.sid = v1.sid AND\n" +
                                "	      m2.mid = v2.mid\n" +
                                "\n" +
                                "	EXCEPT\n" +
                                "\n" +
                                "	SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, null, v2.vathmos\n" +
                                "	FROM vathmologia V1, vathmologia v2,vathmologia v3, mathitis m1, mathima m2\n" +
                                "	WHERE v1.sid=v2.sid AND\n" +
                                "	      v2.sid=v3.sid AND\n" +
                                "	      v1.mid=v2.mid AND\n" +
                                "	      v2.mid=v3.mid AND\n" +
                                "	      v1.etos=v2.etos AND\n" +
                                "	      v2.etos=v3.etos AND\n" +
                                "	      v1.eksamino = 1 AND\n" +
                                "	      v2.eksamino = 2 AND\n" +
                                "	      v3.eksamino = 3 AND\n" +
                                "	      m1.sid = v1.sid AND\n" +
                                "	      m2.mid = v2.mid\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
        try {psql.executeUpdate("CREATE OR REPLACE FUNCTION selVathmFirstTerm()\n" +
                                "returns table (		sid bigint, \n" +
                                "					onoma varchar, \n" +
                                "					eponymo varchar, \n" +
                                "					onoma_mathimatos varchar, \n" +
                                "					etos int,\n" +
                                "					vathmosProtos int					\n" +
                                "					) as\n" +
                                "$$\n" +
                                "\n" +
                                "	SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos\n" +
                                "	FROM vathmologia V1, mathitis m1, mathima m2\n" +
                                "	WHERE v1.eksamino=1 AND\n" +
                                "	      m1.sid = v1.sid AND\n" +
                                "	      m2.mid = v1.mid\n" +
                                "  	EXCEPT\n" +
                                "		SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos\n" +
                                "	FROM vathmologia V1, vathmologia v2, mathitis m1, mathima m2\n" +
                                "	WHERE v1.sid=v2.sid AND\n" +
                                "	      v1.mid=v2.mid AND\n" +
                                "	      v1.etos=v2.etos AND\n" +
                                "	      v1.eksamino = 1 AND\n" +
                                "	      v2.eksamino = 2 AND\n" +
                                "	      m1.sid = v1.sid AND\n" +
                                "	      m2.mid = v2.mid\n" +
                                "\n" +
                                "	EXCEPT\n" +
                                "\n" +
                                "		SELECT v1.sid, m1.onoma, m1.eponymo, m2.onoma_mathimatos, v1.etos, v1.vathmos\n" +
                                "	FROM vathmologia V1, vathmologia v2,vathmologia v3, mathitis m1, mathima m2\n" +
                                "	WHERE v1.sid=v2.sid AND\n" +
                                "	      v2.sid=v3.sid AND\n" +
                                "	      v1.mid=v2.mid AND\n" +
                                "	      v2.mid=v3.mid AND\n" +
                                "	      v1.etos=v2.etos AND\n" +
                                "	      v2.etos=v3.etos AND\n" +
                                "	      v1.eksamino = 1 AND\n" +
                                "	      v2.eksamino = 2 AND\n" +
                                "	      v3.eksamino = 3 AND\n" +
                                "	      m1.sid = v1.sid AND\n" +
                                "	      m2.mid = v2.mid\n" +
                                "$$ LANGUAGE SQL;");} 
        catch (SQLException e) {
            System.out.println("createVathmologiaStoredProcedures : " + e.getMessage());
        }
    }
    private static void createMathitisStoredProcedure(){
        try {DBPostresqlAdmin.getStatement().executeUpdate("CREATE OR REPLACE FUNCTION showtablemathitis()\n" +
                                "returns SETOF mathitis as\n" +
                                "$$\n" +
                                "SELECT * FROM mathitis;\n" +
                                "$$ LANGUAGE SQL");} 
        catch (SQLException e) {
            System.out.println("createMathitisStoredProcedure, showtablemathitis : " + e.getMessage());
        }
        
        
        
        try {DBPostresqlAdmin.getStatement().executeUpdate("CREATE OR REPLACE FUNCTION showtablemathitisyearly(int)\n" +
                                "returns SETOF mathitis as\n" +
                                "$$\n" +
                                "SELECT * \n" +
                                "FROM mathitis\n" +
                                "WHERE etosEisagogis = $1;\n" +
                                "$$ LANGUAGE SQL");} 
        catch (SQLException e) {
            System.out.println("createMathitisStoredProcedure, showtablemathitisyearly(int) : " + e.getMessage());    
        }
    }
    private static void createMathimaStoredProcedure(){
        
        try {DBPostresqlAdmin.getStatement().executeUpdate("CREATE OR REPLACE FUNCTION showtablemathima()\n" +
                "returns table (mid int, onoma_mathimatos varchar, onoma_kathigiti varchar , epitheto_kathigiti varchar)as\n" +
                "$$\n" +
                "SELECT M.mid, M.onoma_mathimatos, K.onoma_kathigiti, K.epitheto_kathigiti \n" +
                "FROM mathima M, kathigitis K \n" +
                "WHERE M.kid=K.kid;\n" +
                "\n" +
                "$$ LANGUAGE SQL");
        }catch (SQLException e) {
            System.out.println("createMathimaStoredProcedure : " + e.getMessage());
        }
    }
    private static void createKathigitisStoredProcedure(){
        try {DBPostresqlAdmin.getStatement().executeUpdate("CREATE OR REPLACE FUNCTION showtablekathigitis()\n" +
                        "returns SETOF kathigitis as\n" +
                        "$$\n" +
                        "SELECT * FROM kathigitis;\n" +
                        "\n" +
                        "$$ LANGUAGE SQL;");
        }catch (SQLException e) {
            System.out.println("createKathigitisStoredProcedure : " + e.getMessage());
        }
    }
    
    
    
    
}