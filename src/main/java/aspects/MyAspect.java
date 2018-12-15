/*
 * LoggingWithAspectJ - Logging with AspectJ
 * Copyright (C) 2007 Christian Schenk
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */
package aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.AfterAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.model.Statistics;
import io.swagger.repository.StatisticsRepository;

@Aspect
@Component
public class MyAspect {
	@Autowired
	StatisticsRepository statisticRepository;
	/**
	 * Will log every execution of Users methods
	 */
//	@Around("execution(* io.swagger.controller.UserController.*(..))")
//	public Object doThing(final ProceedingJoinPoint thisJoinPoint) throws Throwable {
//		final String joinPointName = thisJoinPoint.getThis().getClass().getSimpleName() + "." + thisJoinPoint.getSignature().getName() + "()";
//		System.out.println("Entering [" + joinPointName + "]");
//		Object retVal = thisJoinPoint.proceed();
//		System.out.println("Leaving  [" + joinPointName + "]");
//		return retVal;
//	
//	
//	}
	
	 @Before("execution(* io.swagger.controller.UserController.addUser(..))")
     public void before_addUser()
     {
         System.out.println("FINDALL METHOD USED123");
         long id = 1;
         Statistics stat = statisticRepository.getOne(id);
         stat.setmethodcalls(stat.getMethodcalls()+1);
         statisticRepository.save(stat);
         System.out.println("FINDALL METHOD USED1234");
     }
	
	 @After("execution(* io.swagger.controller.UserController.login(..))")
     public void after_login()
     {
         System.out.println("FINDALL METHOD USED12356767");
         long id = 2;
         Statistics stat = statisticRepository.getOne(id);
         stat.setmethodcalls(stat.getMethodcalls()+1);
         statisticRepository.save(stat);
         System.out.println("FINDALL METHOD USED1234erterte");
     }
	 
//	@AfterReturning(pointcut = "execution(* io.swagger.model.User.*(..))", 
//			   returning = "retVal")
//	public void afterReturningAdvice(JoinPoint jp, Object retVal){
//		   System.out.println("Method Signature: "  + jp.getSignature());  
//		   System.out.println("Returning:" + retVal.toString() );
//		}
//	
//	public class CountingAfterReturningAdvice implements AfterAdvice {
//
//	    private int count;
//
//	    public void afterReturning(Object returnValue, Method doThing, Object[] args, Object target)
//	            throws Throwable {
//	        ++count;
//	        Statistics statistics;
//	    	int val1=0;
//	    	Statistics statistics2;
//	    	public void setStatistics(Statistics statistics) {
//	    		this.statistics = statistics;
//	    }
//
//	    public int getCount() {
//	        return count;
//	    }
	
	
	
	

//		
//			getQuantity();
//	Statistics           setQuantity(val1++);
//	statistics.serviceName(dojoinPointName);
}