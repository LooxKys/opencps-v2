<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row panel">
    <div class="row-header">
        <div class="background-triangle-big">CHI TIÊT THỦ TỤC HÀNH CHÍNH</div>
    </div>
    <div class="panel-body" id="dataDetailServiceInfo">
        <div class="row MB15">
            <div class="col-sm-12">
                <span class="MR10" id="maxLevel" data-bind="text:maxLevel"> </span> <span data-bind="text:serviceName" class="text-bold" id="serviceName"> </span>
            </div>
        </div>
        <div id="serviceinfo_detail_tabstrip" class="row nav-tabs-wrapper">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a data-toggle="tab" href="#ttc">
                        Thông tin chung
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#ttth">
                        Trình tự thực hiện
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#tphs">
                        Thành phần hồ sơ
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#ycdk">
                        Yêu cầu điều kiện
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="ttc" class="tab-pane fade in active">
                    <div class="row-parts-content">
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Cơ quan quản lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:administrationName" id="administrationName"></p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="col-sm-2">
                                <label class="MB10">Lĩnh vực</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:domainName" id="domainName"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Cách thực hiện</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:methodText" id="methodText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Thời gian giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:durationText" id="durationText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Đối tượng</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:applicantText" id="applicantText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Kết quả giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:resultText" id="resultText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Lệ phí</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:feeText" id="feeText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Căn cứ pháp lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:regularText" id="regularText"></p>
                            </div>
                        </div>
                        <input type="hidden" name="serviceinfoId" id="serviceinfoId" data-bind="value : serviceinfoId">
                        <div class="col-sm-12 PL0 MT10 submitDossier">

                        </div>
                    </div>
                </div>
                <div id="ttth" class="tab-pane fade">
                 <p class="" data-bind="text:processText" id="processText"></p>

                 <div class="col-sm-12 PL0 MT10 submitDossier">

                 </div>
             </div>
             <div id="tphs" class="tab-pane fade">
                 <p class="MB15 MT10" data-bind="text:dossierText" id="dossierText"></p>

                 <label>File biểu mẫu</label>
                 <ul class="ML10" id ="service_info_filetemplate" data-template="service_info_filetemplate_template" data-bind="source: fileTemplates">

                 </ul>
                 <script type="text/x-kendo-template" id="service_info_filetemplate_template">
                     <li class="clearfix item-serviceinfo-filetemplate eq-height-lg" data-bind="attr: {data-pk : fileTemplateNo}" role="option" aria-selected="true">
                        <a data-bind="attr : { href: fileTemplateDownLoad}"><i class="fa fa-download" aria-hidden="true"></i> <span data-bind="text: templateName"></span></a>
                    </li>
                </script>

                <div class="col-sm-12 PL0 MT10 submitDossier">

                </div>
            </div>
            <div id="ycdk" class="tab-pane fade">
                <p class="MT10 MB10" data-bind="text:conditionText" id="conditionText"></p>

                <div class="col-sm-12 PL0 MT10 submitDossier">

                </div>
            </div>
        </div>
    </div>
</div>

<div id="submitDossierModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Bạn hãy chọn cơ quan thực hiện</h4>
            </div>
            <div class="modal-body">
                <form id="fmServiceinfo">
                    <div class="row">
                        <div class="col-sm-12 text-center">
                            <input class="form-control" name="slAdministration" id="slAdministration">
                        </div>
                        <div class="col-sm-12 text-center MT15">
                            <button class="btn btn-active" id="btn-submit-serviceinfo" type="button"> Đồng ý</button><button class="btn ML10" data-dismiss="modal"> Hủy bỏ</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
       $("#slAdministration").kendoComboBox({
        placeholder : "Chọn cơ quan thực hiện",
        dataTextField : "govAgencyName",
        dataValueField : "govAgencyCode",
        dataSource : {
            transport : {
                read : {
                    url : "${api.server}/serverconfigs?service="+$("#serviceinfoId").val()+"&applicant=${applicant}",
                    dataType : "json",
                    type : "GET",
                    beforeSend: function(req) {
                        req.setRequestHeader('groupId', ${groupId});
                    },
                    success : function(result){

                    },
                    error : function(xhr){

                    }
                }
            },
            schema : {
                data : "data",
                total : "total",
                model : {
                    id : "id"
                }
            }
        },
        filter: "contains"
    });
   });

    var pullDataDetail= function(id){
        console.log(id);
        $.ajax({
            url : "${api.server}/serviceinfos/"+id,
            dataType : "json",
            type : "GET",
            success : function(result){
                var viewModel = kendo.observable({
                    maxLevel: function(){
                        if(result.maxLevel == 1){
                            $("#maxLevel").addClass("label label-dvc-lv1");
                        }else if(result.maxLevel == 2){
                            $("#maxLevel").addClass("label label-dvc-lv2");
                        }else if(result.maxLevel == 3){
                            $("#maxLevel").addClass("label label-dvc-lv3");
                        }else {
                            $("#maxLevel").addClass("label label-dvc-lv4");
                        }

                        if(result.maxLevel>=3){
                            $(".submitDossier").html('<button type="button" class="btn btn-active" data-toggle="modal" data-target="#submitDossierModal">Nộp hồ sơ >></button>');
                        }else{
                            $(".submitDossier").html('<button class="btn">Xem hướng dẫn >></button>');
                        }
                        return "Mức "+result.maxLevel;
                    },
                    fileTypetemp : function(e){
                        if(e.fileType == "txt"){
                            return "fa fa-file-text-o";
                        }else if(e.fileType == "doc" || e.fileType == "docx"){
                            return "fa fa-file-word-o";
                        }else if(e.fileType == "jpg"){
                            return "fa fa-file-image-o";
                        }else if(e.fileType == "pdf"){
                            return "fa fa-file-pdf-o";
                        }
                    },
                    fileTemplateDownLoad : function(e){
                        var serviceInfoId = $("#serviceinfoId").val();
                        return '${api.server}/serviceinfos/'+serviceInfoId+'/filetemplates/'+e.fileTemplateNo
                    },
                    serviceinfoId: result.serviceInfoId,
                    serviceName: result.serviceName,
                    administrationName: result.administrationName,
                    domainName: result.domainName,
                    resultText: result.resultText,
                    feeText: result.feeText,
                    methodText : result.methodText,
                    durationText : result.durationText,
                    dossierText : result.dossierText,
                    processText : result.processText,
                    applicantText : result.applicantText,
                    regularText : result.regularText,
                    conditionText : result.conditionText,
                    fileTemplates : result.fileTemplates
                });

                kendo.bind($("#dataDetailServiceInfo"), viewModel);
            },
            error : function(xhr){

            }
        });
    }

    $(function(){
        $("#btn-submit-serviceinfo").click(function(){
            var data = $("#fmServiceinfo").serialize();
            var id = $("#serviceinfoId").val();
            $.ajax({
                url : "${api.server}/serviceinfos/"+id,
                dataType : "json",
                type : "PUT",
                contentType : "application/x-www-form-urlencoded",
                success : function(result){

                },
                error : function(xhr){

                }
            });
        });
    });
</script>
