package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public final class Audit {
    private static Audit audit = null;

    public static Audit getAuditInstance(){
        if (audit == null)
            audit = new Audit();
        return audit;
    }

    private Audit(){}

    public void auditLog(String action){
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        ArrayList<String> info = new ArrayList<String>();
        info.add(action); info.add(currentTime.toString());
        info.add(Thread.currentThread().getName());
        Database.getDatabaseInstance().addToCsv("audit.csv", info);
    }
}