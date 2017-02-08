var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
Ext.define('demo.view.UserAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.userAddView',
	title : '新建学生',
	layout : 'border',
    border:false,
	autoShow : false,
	autoScroll : true,
	autoHeight : true,
	height : 200,
	width : 330,
	draggable : true,
	modal : true,
	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			bodyStyle : {
				padding : '10px'
			},
			border : false,
			region : 'center',
			width : '100%',
			height : '100%',
			items : [{
				fieldLabel : '用户ID',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'id'
			},{
				fieldLabel : '学生号',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'number'
			},{
				fieldLabel : '姓名',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'name'
			}, {
				fieldLabel : '性别',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'sex'
			}, {
				fieldLabel : '年龄',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'age'
			}, {
				fieldLabel : '班级',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'stuclass'
			}, {
				fieldLabel : '住址',
				xtype : 'textfield',
				allowBlank : false,
				labelAlign : "right",
				afterLabelTextTpl : required,
				name : 'address'
			}]
		} ];

		this.buttons = [ {
			text : '确定',
			action : 'add'
		}, {
			text : '取消',
			scope : this,
			handler : function() {
				this.close();
			}
		} ];
		this.callParent(arguments);
	}
});