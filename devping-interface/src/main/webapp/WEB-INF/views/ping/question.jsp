<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-body">
			<label>My Point : {{ summary.point }}</label><br/>
			<label>질문은 기본 200 포인트에 추가 포인트를 더한 포인트를 사용합니다.</label>
			<div class="row">
				<div class="form-inline col-md-11">
					<span>200 Point + </span>
					<input class="form-control" id="consume-point" type="text" placeholder="0" data-ng-model="consumePoint"> Point
				</div>
				<div class="col-md-1">
					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Ping</button>
				</div>
			</div>
		</div>
	</div>
	<div>
		<ul class="jb-ping-target-list">
			<li class="jb-ping-target-listitem" data-ng-repeat="expert in selectedExperts | orderBy:'point':reverse=true" data-ng-if="expert.checked == true">
				<div class="jb-summary-unit">
					<label>{{ expert.name }}</label><br/>
					<label>Point : {{ expert.point }}</label>
				</div>
			</li>
		</ul>
	</div>
	<div>
		<textarea class="form-control" rows="20" data-ng-model="summaryQuestion"></textarea>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title">Chatting</h4>
			</div>
			<div class="modal-body">
				200 point로 다음과 같은 질의를 요청합니다.
				<br/>
				<br/>
				{{ summaryQuestion }}
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
