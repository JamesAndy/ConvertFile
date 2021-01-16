package flow

import org.convert.file.File

class MainFlow {

    //已處理的檔案數
    private static int trunFiles;

    //參數設定:設定轉碼目標
    private final static String trunEncode = "UTF-8"
    //參數設定:起始目錄
    private final static String startPath = "D:/Wezoomtek/swjweb"
    //參數設定:篩選檔案
    private final static String[] pFilterL = [
            ".jsp",
            ".java",
            ".js",".css",".txt",".properties"
    ]
    //參數設定:目的路徑
    private final static String purposePath = "C:/Desktop/swjweb"
    //排除路徑
    private final static String[] exPathL = [
            "build","dist","nbproject","PA-DOC",".git"
    ]
    private final static LinkedHashMap replaceStringL = [
            'pageEncoding="big5"':'pageEncoding="UTF-8"',
            'pageEncoding="Big5"':'pageEncoding="UTF-8"',
            'pageEncoding="BIG5"':'pageEncoding="UTF-8"',
            'charset="big5">':'charset="UTF-8">',
            'charset="big5">;':'charset="UTF-8">;',
            'request.setCharacterEncoding("big5");':'request.setCharacterEncoding("UTF-8");',
            'request.setCharacterEncoding("BIG5");':'request.setCharacterEncoding("UTF-8");',
            '"text/html;charset=ms950"'         :'"text/html; charset=UTF-8"',
            '"text/html;charset=Ms950"'         :'"text/html; charset=UTF-8"',
            '"text/html;charset=MS950"'         :'"text/html; charset=UTF-8"',
            '"text/html; charset=ms950"'        :'"text/html; charset=UTF-8"',
            '"text/html; charset=Ms950"'        :'"text/html; charset=UTF-8"',
            '"text/html; charset=MS950"'        :'"text/html; charset=UTF-8"',
            '"text/html; charset=big5"'         :'"text/html; charset=UTF-8"',
            '"text/html; charset=Big5"'         :'"text/html; charset=UTF-8"',
            '"text/html; charset=BIG5"'         :'"text/html; charset=UTF-8"',
            '"text/html;charset=big5"'          :'"text/html; charset=UTF-8"',
            '"text/html;charset=Big5"'          :'"text/html; charset=UTF-8"',
            '"text/html;charset=BIG5"'          :'"text/html; charset=UTF-8"',
            '"text/html; charset = big5"':'contentType="text/html; charset=UTF-8"',
            'response.setContentType("text/html; charset=big5");':
                    'response.setContentType("text/html; charset=UTF-8");',
            'request.getContextPath()%>/resources/js/function.min.js" charset="big5"':
                    'request.getContextPath()%>/resources/js/function.min.js" charset="UTF-8"',
            'src="<%=request.getContextPath()%>/resources/ajax/LoadingPage.js" charset="big5"':
                    'src="<%=request.getContextPath()%>/resources/ajax/LoadingPage.js" charset="UTF-8"',
            '<%=request.getContextPath()%>/resources/js/map/map.layer.js" charset="BIG5"':
                    '<%=request.getContextPath()%>/resources/js/map/map.layer.js" charset="UTF-8"',
            '<%=request.getContextPath()%>/resources/js/map/map.ui.js" charset="BIG5"':
                    '<%=request.getContextPath()%>/resources/js/map/map.ui.js" charset="UTF-8"',
            '<%=request.getContextPath()%>/resources/js/map/map.func.js" charset="BIG5"':
                    '<%=request.getContextPath()%>/resources/js/map/map.func.js" charset="UTF-8""',
            '<%=request.getContextPath()%>/resources/js/map/map.func.searchAGY.js" charset="BIG5"':
                    '<%=request.getContextPath()%>/resources/js/map/map.func.searchAGY.js" charset="UTF-8""',
            '<%=request.getContextPath()%>/resources/js/map/map.func.buffer.js" charset="BIG5"':
                    '<%=request.getContextPath()%>/resources/js/map/map.func.buffer.js" charset="UTF-8""',
            '<%=contextPath + srcPath + "/js/function.min.js?version=20150608"%>" charset="big5"':
                    '<%=contextPath + srcPath + "/js/function.min.js?version=20150608"%>" charset="UTF-8""',
            'src="<%=request.getContextPath()%>/resources/js/Chart.min.js" charset="big5"':
                    'src="<%=request.getContextPath()%>/resources/js/Chart.min.js" charset="UTF-8""',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/CheckData.js"+version+"\\" charset=\\"big5\\"></script>");':
                    'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/CheckData.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/DateUtil.js"+version+"\\" charset=\\"big5\\"></script>");':
                    'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/DateUtil.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine-zh.js"+version+"\\" charset=\\"big5\\"></script>");':
                    'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine-zh.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/function.min.js?version=20150608\\" charset=\\"big5\\"></script>");':
                    'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/function.min.js?version=20150608\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/ajax/LoadingPage.js"+version+"\\" charset=\\"big5\\"></script>");':
                    'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/ajax/LoadingPage.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine.3.0.0-zh_TW.js"+version+"\\" charset=\\"big5\\"></script>");':
                    'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine.3.0.0-zh_TW.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
    ]


    static void main(String[] args){

        //取得要轉檔的檔案路徑
        File.getPathDir(startPath,pFilterL,exPathL)

        List<String> findLineL = []

        String action = "convertAndReplace"

        switch (action){
            case "search":
                //僅搜尋檔案
                File.gFileList.stream()
                        .filter({ fileSourceDirI -> fileSourceDirI != "" &&  File.checkEncoding(fileSourceDirI) != null})
                        .filter({fileSourceDirI ->  File.checkEncoding(fileSourceDirI) != null})
                        .forEach({ fileSourceDirI ->
                            File.searchFileContent(
                                fileSourceDirI,
                                File.checkEncoding(fileSourceDirI),
                                "setQueryTimeOut"
                            ).stream()
                                .filter({stringI -> findLineL.indexOf(stringI) == -1})
                                .each {stringI ->
                                    findLineL.add(stringI)
                                }
                            trunFiles ++
                        })

                println "==============================================="
                File.getFindList().each {
                    println it
                }
                break
            case "convertAndReplace":
                println "convertAndReplace"
                //取代檔案內容並轉檔
                File.gFileList.stream()
                    .filter({ fileSourceDirI -> fileSourceDirI != "" &&  File.checkEncoding(fileSourceDirI) != null})
                    .filter({fileSourceDirI ->  File.checkEncoding(fileSourceDirI) != null})
                    .forEach({ fileSourceDirI ->
                        String goalFileDir = fileSourceDirI.replace(startPath,purposePath);

                        File.creatFile(goalFileDir)
//                        println "${fileSourceDirI} -> ${goalFileDir}"

                        File.convertFileCode(
                                fileSourceDirI,
                                goalFileDir,
                                File.checkEncoding(fileSourceDirI),
                                trunEncode,
                                replaceStringL
                        )
                        trunFiles ++
                    })
                break
            case "convert":
                println "convert"
                //轉檔
                File.gFileList.stream()
                        .filter({ fileSourceDirI -> fileSourceDirI != "" &&  File.checkEncoding(fileSourceDirI) != null})
                        .filter({fileSourceDirI ->  File.checkEncoding(fileSourceDirI) != null})
                        .forEach({ fileSourceDirI ->
                            String goalFileDir = fileSourceDirI.replace(startPath,purposePath);

                            File.creatFile(goalFileDir)

                            File.convertFileCode(
                                    fileSourceDirI,
                                    goalFileDir,
                                    File.checkEncoding(fileSourceDirI),
                                    trunEncode
                            )
                            trunFiles ++
                        })
                break
            default:
                println "無此動作"
        }



        println "共計${trunFiles}個檔案"

    }
}
