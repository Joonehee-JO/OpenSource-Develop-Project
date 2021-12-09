#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 기능 : 카테고리, 정렬, 검색을 고유 int 값을 할당하여 해당 기능에 맞는 메소드 호출 및 경매에 등록된 상품들을 받아와 4열로 정렬시킴
#최종 완성일자 : 12월 1일
###########################
<%
  		String id = null;
		String sort = null;
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		sort = request.getParameter("sort");
		int num = 0;
  		if(session.getAttribute("user_id") != null){
  			id = (String) session.getAttribute("user_id");
  		}
  		if(category != null){
  			num = 1;
  		}
  		if(sort != null){
  			//num = 2;
  			if(sort.equals("popularity")){
  				num = 4;
  				if(category.equals("null")){
  	  				num = 5;
  	  			}
  			}	
  			if(sort.equals("latest")){
  				num = 2;
  				if(category.equals("null")){
  	  				num = 6;
  	  			}
  			}	
  		}
  		if(search != null){
  			num=3;
  		}
  		
  		int pageNumber = 1;
  	%>
    </table>
            		<% 	
                	if (num == 1){
                		Vector<Bean> vec = prDAO.categorySelectProduct(category);
               		%>
                	<table style="margin-left:70px;">	
               		<%
                		int r = 0;
                		
                		for(int i=0; i<vec.size(); i++){	
          					Bean bean = vec.get(i); //벡터에 담긴 빈클래스를 하나씩 추출
          					if(bean.getBbsavailable()==0){
            					continue;
            				}
          					String address = bean.getAddress();
          					if(r%4 == 0){
        			%>
         	                <tr class="box2">
                    <%
          					}
                   	%>
                   	<td>
    	               	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
		                  <a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
		                  <h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
		                  <p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
		                  <p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
		                  <a href="#" class="btn btn-secondary">Auction</a>
		                </div>           	
                   	</td>
              		<%
          					r+=1;			
       					}
               		%>
               		</table>
               		<%
                	}
      				%>
          	</table>
