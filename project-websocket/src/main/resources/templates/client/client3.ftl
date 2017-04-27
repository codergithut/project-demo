<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chat</title>
    <link rel="stylesheet" type="text/css" href="css/lib/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="css/index.css" />
    <script src="js/lib/jquery-3.1.1.min.js"></script>
    <script src="js/lib/namespace.js"></script>
    <script src="js/lib/plugin.js"></script>
    <script src="js/lib/scroll.js"></script>
</head>
<body>

    <div class="chat">
        <div class="chat-box">
            <!--内容区-->
            <div class="chat-content">
                <div class="chat-content-box">
                    <div class="chat-content-left">
                        <!--个人信息-->
                        <div class="chat-content-header">
                            <div class="chat-content-header-icon">
                                <img src="img/icon.jpg" alt="pic" />
                                <p>姓名</p>
                            </div>
                            <div class="chat-content-header-more">
                                <span class="fa fa-bars fa-lg chat-content-header-icon chat-content-header-show"><i class="fa fa-sort-down chat-content-header-show"></i></span>
                                <!--添加好友等操作-->
                                <div class="chat-drop-down chat-content-header-fn">
                                    <ul>
                                        <li class="chat-modal-aim" data-modal="chatAdd"><i class="fa fa-user-o"></i>添加好友</li>
                                        <li class="chat-modal-aim" data-modal="chatLaunch"><i class="fa fa-comment-o"></i>发起聊天</li>
                                        <li class="chat-modal-aim" data-modal="chatEdit"><i class="fa fa-edit"></i>修改资料</li>
                                        <li><i class="fa fa-bell-o"></i>关闭桌面通知</li>
                                        <li><i class="fa fa-power-off"></i>退出</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--搜索-->
                        <div class="chat-content-search">
                            <span class="fa fa-search"></span>
                            <input placeholder="搜索" />
                        </div>
                        <!--选项卡-->
                        <ul class="chat-content-tab">
                            <li class="chat-content-tab-reader chat-content-tab-active"><span class="fa fa-comment fa-lg"></span><i></i></li>
                            <li class="chat-content-tab-friends"><span class="fa fa-user-o fa-lg"></span></li>
                        </ul>
                        <div class="chat-content-tab-ctrl">
                            <!--聊天-->
                            <div class="chat-content-reader-maxBox">
                                <div class="chat-content-reader">
                                    <!--聊天好友列表模板-->
                                    <div class="chat-content-reader-box">
                                        <div class="chat-content-reader-icon">
                                            <img src="#" alt="pic" />
                                        </div>
                                        <div class="chat-content-reader-info">
                                            <span class="chat-content-reader-name"></span>
                                            <span class="chat-content-reader-time"></span>
                                            <span class="chat-content-reader-message"></span>
                                        </div>
                                    </div>
                                    <!--聊天好友群聊模板-->
                                    <div class="chat-content-reader-box chat-content-reader-group">
                                        <div class="chat-content-reader-icon">
                                        </div>
                                        <div class="chat-content-reader-info">
                                            <span class="chat-content-reader-name"></span>
                                            <span class="chat-content-reader-time"></span>
                                            <span class="chat-content-reader-message"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--好友-->
                            <div class="chat-content-friends-box chat-content-hide">
                                <div class="chat-content-friends">
                                    <!--好友模板-->
                                    <div class="chat-content-friends-scope">
                                      <span></span>
                                    </div>
                                    <div class="chat-content-friends-info">
                                        <div class="chat-content-friends-icon">
                                            <img src="#" alt="pic" />
                                        </div>
                                        <div class="chat-content-friends-name">
                                            <span></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--右侧内容-->
                    <div class="chat-content-right">
                        <!--聊天框-->
                        <div class="chat-content-talk">
                            <div class="chat-content-talk-head">
                                <div class="chat-content-talk-headBox">
                                    <div class="chat-content-talk-headName"></div>
                                </div>
                            </div>
                            <!--聊天内容显示框-->
                            <div class="chat-content-talk-body">
                                <!--空白时的内容-->
                                <div class="chat-content-talk-none">
                                    <i class="fa fa-tv fa-lg"></i>
                                    未选择聊天
                                </div>

                                <p class="chat-content-height"></p>
                                <!--对话-->
                                <div class="chat-content-talk-box chat-content-talk-hide">
                                    <div class="chat-content-talk-time">
                                        <div></div>
                                    </div>

                                    <div class="chat-content-talk-text">
                                        <div class="chat-content-talk-pic">
                                            <img src="#" alt="pic" />
                                        </div>
                                        <div class="chat-content-talk-word">
                                            <p></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--聊天内发送框-->
                            <div class="chat-content-talk-input chat-content-talk-hide">
                                <textarea class="chat-content-talk-textArea"></textarea>
                                <div class="chat-content-talk-send">
                                    <span>按下Ctrl+Enter换行</span>
                                    <a>发送</a>
                                </div>
                            </div>
                        </div>

                        <!--切换到朋友标签的信息显示框-->
                        <div class="chat-content-info chat-content-hide">
                            <div class="chat-content-info-head">
                                <div class="chat-content-info-headBox">
                                    <div class="chat-content-info-headName">详细信息</div>
                                </div>
                            </div>
                            <div class="chat-content-info-none">
                                <i class="fa fa-user-circle fa-lg"></i>
                            </div>
                            <div class="chat-content-info-body chat-content-info-hide">
                                <!--空白时的内容-->
                                <div class="chat-content-info-icon">
                                    <img src="#" alt="pic" />
                                </div>
                                <!--姓名-->
                                <div class="chat-content-info-name">
                                    <span></span>
                                    <!--删除和修改备注操作-->
                                    <i class="fa fa-angle-down chat-content-info-more"></i>
                                    <div class="chat-drop-down chat-content-info-modify">
                                        <ul>
                                            <li class="chat-content-remark-modify">修改备注</li>
                                            <li class="chat-content-friend-delete">删除好友</li>
                                        </ul>
                                    </div>
                                </div>
                                <!--标签-->
                                <div class="chat-content-info-tag"></div>
                                <!--备注,地址-->
                                <div class="chat-content-info-box">
                                    <div class="chat-content-info-remark">
                                        <span></span>
                                        <div>
                                            <span>备注: </span>
                                            <input autofocus />
                                        </div>
                                    </div>
                                    <div class="chat-content-info-address">
                                        <span></span>
                                    </div>
                                </div>
                                <!--消息-->
                                <div class="chat-content-info-message">
                                    <a>发消息</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--页脚-->
            <footer>
                <p>
                    © 2017 - 2018 A331 Info. All Rights Reserved
                </p>
            </footer>
        </div>
    </div>

    <!--右键清屏-->
    <div class="chat-right-click chat-clear">
        <ul>
            <li>清屏</li>
        </ul>
    </div>

    <!--对正在会话好友的右键操作-->
    <div class="chat-right-click chat-delete">
        <ul>
            <li class="chat-delete-top">置顶</li>
            <li class="chat-delete-close">关闭聊天</li>
        </ul>
    </div>

    <!--添加好友模态弹出框-->
    <div class="chat-modal" id="chatAdd">
        <div class="chat-modal-mask"></div>
        <div class="chat-modal-box">
            <div class="chat-modal-content">
                <div class="chat-modal-head">
                    <h4>添加好友</h4>
                    <span class="fa fa-close chat-modal-close"></span>
                </div>
                <div class="chat-modal-body">
                    <div>
                        <input id="addAccount" name="addAccount" placeholder="请输入好友账号" />
                    </div>
                </div>
                <div class="chat-modal-foot">
                    <a class="chat-modal-close">关闭</a>
                    <a class="chat-modal-foot-save">确定</a>
                </div>
            </div>
        </div>
    </div>
    <!--发起聊天模态弹出框-->
    <div class="chat-modal" id="chatLaunch">
        <div class="chat-modal-mask"></div>
        <div class="chat-modal-box">
            <div class="chat-modal-content">
                <div class="chat-modal-head">
                    <h4>发起聊天</h4>
                    <span class="fa fa-close chat-modal-close"></span>
                </div>
                <div class="chat-modal-body">
                    <div class="chat-launch-body-head"></div>
                    <div class="chat-launch-body-box">
                        <div>
                            <i class="fa fa-circle-thin "></i>
                            <img src="#" alt="pic">
                            <span></span>
                        </div>
                    </div>
                </div>
                <div class="chat-modal-foot">
                    <a class="chat-modal-close">关闭</a>
                    <a class="chat-modal-foot-save">确定<span></span></a>
                </div>
            </div>
        </div>
    </div>
    <!--修改资料模态弹出框-->
    <div class="chat-modal" id="chatEdit">
        <div class="chat-modal-mask"></div>
        <div class="chat-modal-box">
            <div class="chat-modal-content">
                <div class="chat-modal-head">
                    <h4>修改资料</h4>
                    <span class="fa fa-close chat-modal-close"></span>
                </div>
                <div class="chat-modal-body">
                    <div>
                        <label for="editName">昵称: </label><input name="editName" id="editName" />
                    </div>
                    <div>
                        <label for="editTag">标签: </label><input name="editTag" id="editTag" />
                    </div>
                    <div>
                        <label for="editAddress">地址: </label><input name="editAddress" id="editAddress" />
                    </div>
                </div>
                <div class="chat-modal-foot">
                    <a class="chat-modal-close">关闭</a>
                    <a class="chat-modal-foot-save">确定</a>
                </div>
            </div>
        </div>
    </div>
    <input name = "token"  id = "token" type = "hidden" value = "${token}" />
    <input class="user-id" id = "userid" style="display: none" value = "${userid}" />

    <script src="js/index.js"></script>
    <script src="js/chat.js"></script>
    <script src="js/view.js"></script>

</body>
</html>