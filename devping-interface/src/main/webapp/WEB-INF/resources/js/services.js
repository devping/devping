devPingApp.service('summaryService', function(){
	this.getSummary = function(){
		return summary;
	};
	
	var summary = {
			name : '오창윤',
			point : 1000
	};
});

devPingApp.service('pingSearchService', function(){
	this.getMoreSelectedTags = function(){
		return moreSelectedTags;
	};
	
	this.getExpertGroup = function(){
		return expertGroup;
	};
	
	this.addTag = function(nowSelectedTags, tag){
		if(!nowSelectedTags || nowSelectedTags.trim() == '')
			return nowSelectedTags = tag;
		
		selectedTagArr = nowSelectedTags.split(",");
		for(var tagNum in selectedTagArr){
			if(selectedTagArr[tagNum].trim() == tag.trim())
				return nowSelectedTags;
		}
		
		return nowSelectedTags += ', ' + tag;
	};
	
	this.searchExperts = function(){
		var proposeExperts = [];
		for(var expertNum in expertGroup){
			var expert = expertGroup[expertNum];
			var exist = false;
			var push = false;
			for(var proposeNum in proposeExperts){
				if(proposeExperts[proposeNum].id == expert.id)
					exist = true;
			}
			if(exist)	continue;
			var skills = expert.skill;
			for(var skillNum in skills){
				for(var tagNum in selectedTagArr){
					if(skills[skillNum].trim() == selectedTagArr[tagNum].trim()){
						proposeExperts.push(expert);
						exist = true;
					}
					if(push)	break;
				}
				if(push)	break;
			}
		}
		return proposeExperts;
	};
	

	var selectedTagArr = [];
	var moreSelectedTags = [ 'java', 'wildfly', 'java script', 'spring', 'sql', 'docker', 'jquery' ];
	var expertGroup = [
			{
				id: 1,
				name: '이주호',
				point: 10000,
				skill: [ 'wildfly', 'docker', 'rhq' ]
			},
			{
				id: 2,
				name: '엄윤식',
				point: 30000,
				skill: [ 'spring', 'spring mvc', 'spring data' ]
			},
			{
				id: 3,
				name: '이태영',
				point: 23000,
				skill: [ 'java script', 'jquery' ]
			},
			{
				id: 4,
				name: '이충렬',
				point: 3200,
				skill: [ ]
			},
			{
				id: 5,
				name: '박현수',
				point: 500,
				skill: [ ]
			},
			{
				id: 6,
				name: '홍상표',
				point: 1000,
				skill: [ 'spring', 'spring mvc', 'spring data' ]
			},
			{
				id: 7,
				name: '나성엽',
				point: 8000,
				skill: [ ]
			},
			{
				id: 8,
				name: '안경섭',
				point:21000,
				skill: [ 'spring', 'spring mvc', 'spring data' ]
			},
			{
				id: 9,
				name: '오창윤',
				point: 10000,
				skill: [ 'spring', 'spring mvc', 'spring data' ]
			}
	];
});

devPingApp.service('pingQuestionService', function(){

});