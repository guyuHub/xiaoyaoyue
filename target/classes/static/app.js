Ext.Loader.setConfig({enabled: true});	
Ext.application({
	name: 'demo',	
	
	appFolder: 'demo',
	
	controllers: [		
		'UserController'
	],
	
	launch: function(){		
		
		Ext.create('Ext.container.Viewport', {
			layout: 'fit',		
			hideBorders: false,
			items: [
				{   
					layout: 'fit',	
					items: [
						{
							
							xtype: 'userView'/*,
							width: '100%',
							height:'100%'*/
						}/*,
						{
							xtype: 'footer',
							region : 'south'
						},
						{
							xtype: 'main',
							region: 'center'
						},
						{
							xtype: 'nav',
							region : 'west'
						},
						{
							xtype: 'tip',
							region : 'east'
						}*/
					]
				}
			]
		});
	}
	
	});