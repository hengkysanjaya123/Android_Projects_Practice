<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 18/12/18
 * Time: 00.00
 */

namespace Acme\Api\Http\Middleware;


use Closure;
use Illuminate\Http\Request;

final class HasRole
{
    /**
     * @param Request $request
     * @param Closure $next
     * @param string ...$roles
     * @return mixed
     */
    public function handle(Request $request, Closure $next, string ...$roles)
    {
        $user = $request->user();

        if (!$user->hasRole($roles)) {
            abort(403, 'You have no access');

            return;
        }

        return $next($request);
    }
}