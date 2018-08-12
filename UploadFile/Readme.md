
# UploadFile
 
    - 将文件通过Web页面上传至FTP服务器的指定文件夹
    - 调用指定地址触发jenkens任务
    
# BI调用
    - 需要在biMsProperty.properties中将指定类型的上传地址和调用地址配置好，以便在bi的不同上传模块中直接输入指定的模块编号即可完成文件的上传。
    - 调用示例http://127.0.0.1:8080?type=0    