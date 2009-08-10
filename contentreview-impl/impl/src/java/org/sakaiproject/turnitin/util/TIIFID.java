package org.sakaiproject.turnitin.util;

public enum TIIFID {
    fid1 (1, new TIIParam[] {TIIParam.aid, TIIParam.diagnostic, TIIParam.dis, 
            TIIParam.encrypt, TIIParam.fcmd, TIIParam.fid, TIIParam.gmtime, 
            TIIParam.said, TIIParam.uem, TIIParam.ufn, TIIParam.uid, TIIParam.uln, 
            TIIParam.upw, TIIParam.username, TIIParam.utp }),
    fid4 (4, new TIIParam[] {TIIParam.aid, TIIParam.assign, TIIParam.assignid,
            TIIParam.ced, TIIParam.cid, TIIParam.ctl, TIIParam.diagnostic,
            TIIParam.dis, TIIParam.dtdue, TIIParam.dtstart, TIIParam.encrypt,
            TIIParam.fcmd, TIIParam.fid, TIIParam.gmtime, TIIParam.newassign,
            TIIParam.said, TIIParam.uem, TIIParam.ufn, TIIParam.uid, 
            TIIParam.uln, TIIParam.upw, TIIParam.username, TIIParam.utp});
    /*2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12 */

    public final int fidnum;
    public final TIIParam[] md5params;
    TIIFID(int fidnum, TIIParam[] md5params) {
        this.fidnum = fidnum;
        this.md5params = md5params;
    }
    
    public boolean includeParamInMD5(String paramname) {
        boolean togo = false;
        for (TIIParam param: md5params) {
            if (param.toString().equals(paramname)) {
                togo = true;
            }
        }
        return togo;
    }

    public static TIIFID getFid(int fid) {
        TIIFID togo = null;
        for (TIIFID fidobj: TIIFID.values()) {
            if (fidobj.fidnum == fid) {
                togo = fidobj;
            }
        }
        return togo;
    }

}