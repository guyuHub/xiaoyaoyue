Ext.define('demo.store.UserStore', {
	extend : 'Ext.data.Store',
	autoLoad : true,
	fields : [ 'id', 'number','name', 'sex', 'age', 'stuclass','address'],
	proxy : {
		type : 'ajax',
		url : '/student/list',
		api : {
			read : '/student/list'
		},
		reader : {
			type : 'json',
			root : 'data',
			successProperty : 'success'
		}
	}
});