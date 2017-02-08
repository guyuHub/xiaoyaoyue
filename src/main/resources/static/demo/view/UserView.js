Ext.define('demo.view.UserView',{
    extend : 'Ext.panel.Panel',
    alias : ['widget.userView'],
    title : '学生列表',
    height : 150,
    layout : {
		type : 'fit'
	},
	items : [{
		xtype : 'grid',
		store : 'UserStore',
		border : false,
		columnLines : true,
		  plugins:[  
	                 Ext.create('Ext.grid.plugin.CellEditing',{  
	                     clicksToEdit:2
	                 })  
	        ],  
		columns : [{
			text : '用户ID',
			dataIndex : 'id',
			flex : 1
		},{
			text : '学生号',
			dataIndex : 'number',
			editor: 'textfield',
			flex : 1
		},{
			text : '姓名',
			dataIndex : 'name',
			cls : 'ux-grid-header',
			align : 'center',
			editor:{  
                allowBlank:true  
            },
			flex : 2,
			renderer : function(val) {
				var value = val.replace(/\s/g, '&nbsp;');
				return value;
			}
		},{
			text : '性别',
			dataIndex : 'sex',
			cls : 'ux-grid-header',
			align : 'center',
			editor: 'textfield',
			flex : 1
		},{
			text : '年龄',
			dataIndex : 'age',
			cls : 'ux-grid-header',
			align : 'center',
			editor:{  
                allowBlank:true  
            },
			flex : 1
		},{
			text : '班级',
			dataIndex : 'stuclass',
			cls : 'ux-grid-header',
			align : 'center',
			editor: 'textfield',
			flex : 1
		},{
			text : '住址',
			dataIndex : 'address',
			cls : 'ux-grid-header',
			align : 'center',
			editor: 'textfield',
			flex : 1
		}, {
            header: '操作',
            xtype: 'actioncolumn',
            items: [{
                icon: '../../resources/images/delete.png',
                tooltip: '删除',
                handler: function (grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                	var deleteMap = {};
                	deleteMap['id'] =rec.get('id') ;
                	alert(rec.get('id'));
            		Ext.Ajax.request({
						url : 'student/delete',
						method : 'POST',
						jsonData : deleteMap,
						success : function(resp) {
							var record = Ext.JSON.decode(resp.responseText);
							if (!record.success) {
								Ext.MessageBox.alert('出错提示', record.error);
							} else {
								grid.getStore().load();
								Ext.MessageBox.alert('新建', '删除成功');
							}
						},
						failure : function(resp) {
							var result = Ext.JSON.decode(resp.responseText);
							if (!result.success) {
								Ext.MessageBox.alert('出错提示', result.error);
							} else {
								Ext.MessageBox.alert('运行错误',
										resp.responseText);
							}
						}
					});
                }
            },{

                icon: '../../resources/images/rank.png',
                tooltip: '更改',
                handler: function (grid, rowIndex, colIndex) {
                    var recc = grid.getStore().getAt(rowIndex);
                	var updateMap = {};
                	updateMap['id']=recc.get('id') ;
                	updateMap['number']=recc.get('number') ;
                	updateMap['name'] =recc.get('name') ;
                	updateMap['sex']=recc.get('sex') ;
                	updateMap['age']=recc.get('age') ;
                  	updateMap['stuclass']=recc.get('stuclass') ;
                  	updateMap['address']=recc.get('address') ;
            		Ext.Ajax.request({
						url : 'student/update',
						method : 'POST',
						jsonData : updateMap,
						success : function(resp) {
							var record = Ext.JSON.decode(resp.responseText);
							if (!record.success) {
								Ext.MessageBox.alert('出错提示', record.error);
							} else {
							
								Ext.MessageBox.alert('新建', '更改成功');
							}
						},
						failure : function(resp) {
							var result = Ext.JSON.decode(resp.responseText);
							if (!result.success) {
								Ext.MessageBox.alert('出错提示', result.error);
							} else {
								Ext.MessageBox.alert('运行错误',
										resp.responseText);
							}
						}
					});
                }
            
            }]
        }],
		tbar : {
			xtype : 'toolbar',
			items :[{
				xtype : 'button',
				itemId : 'add',
				text : '新建',
				tooltip : '新建学生'
			}, {
				xtype : 'tbseparator',
				itemId : 'add_separator'
			},{
				xtype : 'textfield',
				name : 'userName',
				labelAlign : 'right',
				fieldLabel : '登录名'
			}, {
				xtype : 'button',
				action : 'search',
				text : '查询'
			}]
		},
		bbar : {
			xtype : 'pagingtoolbar',
			itemId : 'pagebar',
			store : 'UserStore'
		}
	}],
    
    initComponent : function(){
    	this.callParent(arguments);
    }
});