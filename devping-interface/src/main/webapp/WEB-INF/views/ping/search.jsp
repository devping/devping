<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container" data-ng-app="devPingApp">
	<div class="panel panel-primary">
		<div class="panel-body">
			<label for="tag-group">tag</label>
			<input class="form-control input-lg" id="tag-group" type="text" placeholder="C, C++, java..." data-ng-model="nowSelectedTags" data-ng-change="searchExperts()">
			<ul class="jb-ping-tag-list">
				<li class="jb-ping-tag-listitem" data-ng-repeat="tag in tags" data-ng-click="selectedTag($event, nowSelectedTags, tag)">
					<span class="label label-success">{{ tag }}</span>
				</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<a href="#/write" class="btn btn-primary btn-lg btn-block">확인</a>
			<ul class="jb-ping-target-list">
				<li class="jb-ping-target-listitem" data-ng-repeat="expert in proposeExperts | orderBy:'point':reverse=true">
					<div class="jb-summary-unit">
						<label>{{ expert.name }}</label><br/>
						<label>Point : {{ expert.point }}</label>
					</div>
				</li>
			</ul>
		</div>
		<div class="col-md-10">
			<textarea class="form-control" rows="20" data-ng-model="summaryQuestion"></textarea>
		</div>
	</div>
</div>